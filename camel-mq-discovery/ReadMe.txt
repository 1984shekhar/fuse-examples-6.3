Fabric Activemq Toplogy Example using Fabric discovery.

 LoadBalancing Topology.
    - Choose a group name for the load-balancing cluster.
    - Each broker in the cluster registers with the chosen group.
    - Each broker must be identified by a unique broker name.
    - Normally, each broker is deployed in a separate container. 
    
    container-create-child root broker 2
    mq-create --group loadbal --assign-container broker1 brokerProfile
    mq-create --group loadbal --assign-container broker2 brokerProfile2
    cluster-list
    
    How to discover brokers: use brokerUrl as discovery:(fabric:loadbal).
    container-create-child --profile mq-client-loadbal --profile example-client-fabric-discovery root disoveryClient
    profile-create --parent feature-camel example-client-fabric-discovery
    profile-edit --feature activemq-camel example-client-fabric-discovery
    profile-edit --bundle mvn:com.mycompany/camel-mq-discovery/1.0.0-SNAPSHOT example-client-fabric-discovery 
