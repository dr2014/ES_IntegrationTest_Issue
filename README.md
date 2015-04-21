# Issue
The assertion in class Transports[https://github.com/elastic/elasticsearch/blob/master/src/main/java/org/elasticsearch/transport/Transports.java#L57] is failing integration test for HTTP requests. 

Should a thread name having prefix as [NttyTransport.HTTP_SERVER_WORKER_THREAD_NAME_PREFIX] be classified as TransportThread?   

# Recreate the issue
(1) Run the integration test
gradle test

(2) Run the plugin with assertions enabled
gradle run
http://localhost:9200/test-plugin/status

