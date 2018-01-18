
A simple example where operation is invoked as mbean.
Also it shows how we can get username within the operation.

    osgi:install -s mvn:mbean-expose/mbeanExposeTest/0.0.1-SNAPSHOT
    
    one can test this using
    
    curl  -v -u  admin:admin http://localhost:8181/hawtio/jolokia/exec/com.test.mbean:type=beans,name=exposeMbeanTest/hello


