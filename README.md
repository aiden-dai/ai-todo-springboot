# ai-todo-springboot
Sample ToDo app built via Springboot + Cassandra. Current Springboot Version is 2.2.5.RELEASE

Major Features:
- Microservice Design
- Service Registry and Discovery via Consul
- All in docker


## Start Consul

Start Consul in docker
```bash
docker run \
 -d \
 -p 8500:8500 \
 -p 8600:8600/udp \
 --name=consul \
 consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0
```


## Start Cassandra

Start cassandra in docker and create keyspace
```bash
docker run --rm -d -p 9042:9042 -v /root/data/cassandra:/var/lib/cassandra --name cassandra0 cassandra

docker exec -it cassandra0 cqlsh

Connected to Test Cluster at 127.0.0.1:9042.
[cqlsh 5.0.1 | Cassandra 3.11.6 | CQL spec 3.4.4 | Native protocol v4]
Use HELP for help.
cqlsh> CREATE KEYSPACE todo1
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};
```


## Run backend in Docker

Build the backend image
```
docker build --rm -f "backend/Dockerfile" -t sp-task:latest "backend"
```

Start the backend api
```
docker run -d --name taskapi --rm -p 8081:8080 --link cassandra0:cassandra sp-task:latest
```

Try rest api
```
$ curl -i -X GET localhost:8081/api
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 20 Mar 2020 11:58:10 GMT

{"status":"Success","message":""}
```

Check Consul Services, you should see TaskAPI service in the list.
```
$ curl http://localhost:8500/v1/catalog/services
{"consul":[],"taskAPI":["secure=false"]}
```



## Run frontend in Docker

Build the front image
```
docker build --rm -f "frontend/Dockerfile" -t sp-todo:latest "frontend"
```

Start the frontend
```
docker run -d --name todo --rm -p 8082:8080 sp-todo:latest
```

Visit the Web UI `http://localhost:8082` to check.