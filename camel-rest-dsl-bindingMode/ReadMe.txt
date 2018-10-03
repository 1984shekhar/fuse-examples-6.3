
keytool -genkeypair -keyalg RSA -dname "CN=Client, OU=Engineering, O=Red Hat, ST=Dublin, C=IE" -validity 365 -alias client -keypass KeyPass -keystore clientKeystore.jks -storepass StorePass

keytool -genkeypair -keyalg RSA -dname "CN=Service, OU=Engineering, O=Red Hat, ST=Dublin, C=IE" -validity 365 -alias service -keypass KeyPass -keystore serviceKeystore.jks -storepass StorePass
Camel Router Project for Blueprint (OSGi)
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache ServiceMix
or Apache Karaf. You can run the following command from its shell:

    osgi:install -s mvn:com.mycompany/test-profile/1.0

For more help see the Apache Camel documentation

    http://camel.apache.org/

Request can be send as:

[cpandey@cpandey bin]$ curl -k https://localhost:9091/cbr-web-services/rest/itineraries/hello

[cpandey@cpandey bin]$ curl -H "Content-Type: application/json" -d '{"name":"xyz","id":"xyz123"}' https://localhost:9091/cbr-web-services/rest/itineraries
{"name":"xyz","id":"xyz123"}[cpandey@cpandey bin]$ 
[cpandey@cpandey bin]$ curl -H "Content-Type: application/xml" -d @/home/cpandey/Desktop/text.xml https://localhost:9091/cbr-web-services/rest/itineraries
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<person>
    <id>xyz123</id>
    <name>xyz</name>
</person>
[cpandey@cpandey bin]$ 


While text.xml should be having content:
<person><name>xyz</name><id>xyz123</id></person>
