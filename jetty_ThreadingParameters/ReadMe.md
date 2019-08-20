1. For jetty8(Red Hat Fuse 6.1 and 6.2.1)
```
<Set name="threadPool">
    			<New class="org.eclipse.jetty.util.thread.QueuedThreadPool">
      				<Set name="minThreads">20</Set>
      				<Set name="maxThreads">1000</Set>
    			</New>
  		</Set>
 ```
2. For jetty9(Red Hat Fuse 6.3.0)
```
 <Get name="ThreadPool">
        <Set name="minThreads" type="int">10</Set>
        <Set name="maxThreads" type="int">1000</Set>
        <Set name="idleTimeout" type="int">60000</Set>
        <Set name="detailedDump">false</Set>
    </Get>
```
3. To check number of threads analyse thread-dumps. In Red Hat Fuse 6.3, there is a jetty tab in Hawtio that can be used to easily determine thread configurations.
```
jstack -l 18321 > tdump2.txt
[cpandey@cpandey log]$ grep qtp tdump2.txt|wc -l
28
```
Here 8 threads are extra, out of which 2 are for acceptor threads and 2 for selector. Other 4 threads are always there.

```
These are idle threads
[cpandey@cpandey log]$ grep org.eclipse.jetty.util.thread.QueuedThreadPool.idleJobPoll tdump2.txt|wc -l
24

```
Here 4 threads are extra. I observed that even if you increase minThreads to 30 or 40, if we grep thread-dumps similarly we will get 34 or 44 total number of threads. 


4. For CXF webservices with custom port, follow link[1].


[1]. https://github.com/1984shekhar/fuse-examples-6.3/blob/master/interceptor_soap_http_header_example/src/main/resources/OSGI-INF/blueprint/camel-route.xml#L20-L21

