JBossFuse:karaf@root> profile-display -r testProfile 
Profile id: testProfile
Version   : 1.0
Attributes: 
Containers: csp1

Container settings
----------------------------
Features : 
	camel-blueprint

Bundles : 
	mvn:org.jboss.fuse.samples/camel-spring-property/1.0-SNAPSHOT


Configuration details
----------------------------
PID: result


PID: camel.blueprint
  result profile:myFile.xml



Other resources
----------------------------
Resource: myFile.xml
<?xml version="1.0" encoding="UTF-8"?>
key1=value1



Logs:

2018-12-28 21:19:36,364 | INFO  | 16 - timer://foo | _route1                          | 146 - org.apache.camel.camel-core - 2.17.0.redhat-630356 | route invoked
2018-12-28 21:19:36,374 | INFO  | 16 - timer://foo | TestBean                         | 142 - camel-spring-property.1.0-SNAPSHOT - 1.0.0.SNAPSHOT | <?xml version="1.0" encoding="UTF-8"?>n  | key1=value1n  | 
2018-12-28 21:19:36,374 | INFO  | 16 - timer://foo | _route1                          | 146 - org.apache.camel.camel-core - 2.17.0.redhat-630356 | bean invoked

