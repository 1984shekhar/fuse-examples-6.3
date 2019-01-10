Camel Project for Spring 
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

For more help see the Apache Camel documentation

    http://camel.apache.org/
    
    
    osgi:install -s mvn:com.mycompany/camel-spring/1.0.0-SNAPSHOT
    
    
    
     <sslContext>
            <sslContext
                keyStore="${karaf.base}/etc/.certificates/keystore.jks"
                keyStorePassword="password"
                />
        </sslContext>
        <transportConnectors>
<transportConnector name="nio+ssl" uri="nio+ssl://0.0.0.0:61617?transport.enabledProtocols=TLSv1,TLSv1.1,TLSv1.2&amp;maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600&amp;wireFormat.maxInactivityDuration=5000&amp;keepAlive=true"/>
        </transportConnectors>
        
        
keytool -genkey -alias mydomain -keyalg RSA -keystore keystore.jks -keysize 2048
keytool -export -alias mydomain -file mydomain.crt -keystore keystore.jks
keytool -import -trustcacerts -alias mydomain -file mydomain.crt -keystore clientkeystore.jks
