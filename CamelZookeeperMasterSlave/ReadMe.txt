Camel Project for Blueprint 
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache Karaf.
You can run the following command from its shell:

    osgi:install -s mvn:com.mycompany/camel-zookeeper-example/1.0.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/
    
    
    
    In fabric environment:
profile-create testZK
profile-edit --feature camel-zookeeper --feature camel-blueprint testZK
profile-edit --bundle mvn:com.mycompany/camel-zookeeper-example/1.0.0-SNAPSHOT testZK
container-add-profile abc testZK
container-add-profile pqr testZK
    
    JBossFuse:karaf@root> container-list
[id]   [version]  [type]  [connected]  [profiles]              [provision status]
root*  1.0        karaf   yes          fabric                  success           
                                       fabric-ensemble-0000-1                    
                                       jboss-fuse-full                           
  abc  1.0        karaf   yes          default                 success           
                                       testZK                                    
  pqr  1.0        karaf   yes          default                 success           
                                       testZK                               
JBossFuse:karaf@root> 
    
