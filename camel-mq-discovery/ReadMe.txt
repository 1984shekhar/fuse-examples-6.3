ActiveMq Topologies in Fabric Environment in Red Hat JBoss Fuse.

1) LoadBalancing Topology.
    - Choose a group name for the load-balancing cluster.
    - Each broker in the cluster registers with the chosen group.
    - Each broker must be identified by a unique broker name.
    - Normally, each broker is deployed in a separate container. 
    
    container-create-child root broker 2
    mq-create --group loadbal --kind StandAlone --assign-container broker1 brokerProfile
    mq-create --group loadbal --kind StandAlone --assign-container broker2 brokerProfile2
    cluster-list
    
    How to discover brokers: use brokerUrl as discovery:(fabric:loadbal).
    
Drawback:  This topology does not have shared storage. Hence it is more suited for non persistent messages. 

2)

A) MasterSlave Topology

 The basic rules for creating a master-slave cluster of brokers are as follows:

    Choose a group name for the master-slave cluster.
    Each broker in the cluster registers with the chosen group.
    Each broker must be identified by the same virtual broker name.
    Normally, each broker is deployed in a separate container. 

JBossFuse:karaf@root> mq-create --create-container broker --replicas 2 --data /home/cpandey/activemq/hq-broker --group masterslave hq-broker

BrokerUrl from clients:
discovery:(fabric:masterslave)

Specify Persistent data storage:
mq-create --assign-container broker1 --data /var/activemq/hq-broker hq-broker


profile-create --parent feature-camel example-client-fabric-discovery
    profile-edit --feature activemq-camel example-client-fabric-discovery
	profile-edit --bundle mvn:com.mycompany/camel-mq-discovery/1.0.0-SNAPSHOT example-client-fabric-discovery 
	profile-edit --feature mq-fabric example-client-fabric-discovery 
	container-create-child --profile example-client-fabric-discovery root disoveryClient

The broker group uses ZooKeeper to manage a shared distributed lock that controls ownership of the master status. Using cluster-list, one can identify the master/slave status.


B) Alternative locking mechanism

Fabric based MasterSlave Topology uses zookeeper to manage shared lock.   If the master broker loses connectivity to the Fabric ensemble, it automatically becomes dormant (and ceases to accept incoming messages). A potentially undesirable side effect of this behaviour is that when you perform maintenance on the Fabric ensemble (for example, by shutting down one of the Fabric servers), you will find that the broker cluster shuts down as well. It will come up automatically once ensemble is up but it might take a few seconds more than normal scenarios because of zookeeper locking which entirely depends on ensemble health. 

In some deployment scenarios, therefore, you might get better up times and more reliable broker performance by disabling the Zookeeper locking mechanism (which Fabric employs by default) and using an alternative locking mechanism instead. 

The Apache ActiveMQ persistence layer supports alternative locking mechanisms which can be used to enable a master-slave broker cluster. In order to use an alternative locking mechanism, you need to make at least the following basic configuration changes:

    Disable the default Zookeeper locking mechanism (which can be done by setting standalone=true in the broker's io.fabric8.mq.fabric.server-BrokerName PID).
    Enable the shared file system master/slave locking mechanism in the KahaDB persistence layer (see section "Shared File System Master/Slave" in "Fault Tolerant Messaging"). 

But than user would not be able to use Fabric functionalities like discovery, so to connect these brokers user would have to provide static brokerUrl. Now client url will be something like:
failover:(tcp://broker1:61616,tcp://broker2:61616,tcp://broker3:61616)


https://access.redhat.com/documentation/en-us/red_hat_jboss_fuse/6.3/html/fabric_guide/mq-topol
https://github.com/1984shekhar/fuse-examples-6.3/blob/master/camel-mq-discovery/src/main/resources/OSGI-INF/blueprint/blueprint.xml
