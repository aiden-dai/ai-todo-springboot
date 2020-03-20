# ai-todo-springboot
ToDo app built via Springboot


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

Build the image
```
docker build --rm -f "backend/Dockerfile" -t sp-task:latest "backend"
```

Start the backend
```
docker run --name taskapi --rm -p 8081:8080 --link cassandra0:cassandra sp-task:latest

```

Try rest api
```
curl -i -X GET localhost:8081/api/products
```



## Run frontend in Docker

Build the image
```
docker build --rm -f "frontend/Dockerfile" -t sp-todo:latest "frontend"
```

Start the frontend
```
docker run --name todo --rm -p 8082:8080 --link taskapi:taskapi sp-todo:latest
```

Visit the Web UI `http://localhost:8082` to check.