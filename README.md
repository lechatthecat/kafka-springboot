## How to start this project
### prerequisites
- minikube
- java21 (openjdk21)
- gradle Gradle 8.14.x

### start
1. git clone https://github.com/lechatthecat/kafka-springboot
2. cd kafka-springboot
3. ./gradlew bootJar
4. docker build -t kafka-springboot-test:latest -f docker/springboot/Dockerfile .
5. minikube start
6. minikube image load kafka-springboot-test:latest
7.
```
kubectl apply -f ./k8s/mysql-deployment.yaml
kubectl apply -f ./k8s/redis-configmap.yaml
kubectl apply -f ./k8s/redis-service.yaml
kubectl apply -f ./k8s/redis-statefulset.yaml

wait for a few seconds

kubectl exec -it redis-cluster-0 -- bash

redis-cli --cluster create \
  redis-cluster-0.redis-cluster:6379 \
  redis-cluster-1.redis-cluster:6379 \
  redis-cluster-2.redis-cluster:6379 \
  redis-cluster-3.redis-cluster:6379 \
  redis-cluster-4.redis-cluster:6379 \
  redis-cluster-5.redis-cluster:6379 \
  --cluster-replicas 1

redis-cli -c -h redis-cluster-0.redis-cluster -p 6379 cluster info

kubectl apply -f ./k8s/kafka-kraft-deployment.yaml

kubectl apply -f ./k8s/springboot-deployment.yaml
```

### Check the pods
```
kubectl get pods -w
kubectl logs -f deployment/springboot
kubectl logs -f deployment/mysql
kubectl logs -f redis-cluster-1
kubectl logs -f redis-cluster-2
kubectl logs -f redis-cluster-3
kubectl logs -f redis-cluster-4
kubectl logs -f redis-cluster-5
kubectl logs -f kafka-0
kubectl logs -f kafka-1
kubectl logs -f kafka-2
```

### Check the URL
minikube service springboot --url
http://192.168.49.2:31415
Open the following url to see if it works
http://192.168.49.2:31415/hello

At first, trace the log of speingboot
```
kubectl logs -f deployment/springboot
```

Then, check kafka
http://192.168.49.2:31415/kafka/send?topic=test-topic&msg=hello

Now this log should appear from Kafka
```
✅ [KafkaProducer] Sent message to topic 'test-topic': hello
2025-11-09T09:33:33.218Z  INFO 1 --- [test] [test-producer-1] o.a.k.c.p.internals.TransactionManager   : [Producer clientId=test-producer-1] ProducerId set to 0 with epoch 0
📩 [KafkaConsumer] Received message: hello
✅ [KafkaProducer] Sent message to topic 'test-topic': hello
📩 [KafkaConsumer] Received message: hello
```

## Optional
delete the deployments
```
kubectl delete -f ./k8s/springboot-deployment.yaml
kubectl delete -f ./k8s/redis-statefulset.yaml
kubectl delete -f ./k8s/redis-service.yaml
kubectl delete -f ./k8s/redis-configmap.yaml
kubectl delete -f ./k8s/mysql-deployment.yaml
kubectl delete statefulset kafka
kubectl delete service kafka
kubectl delete service kafka-external
kubectl delete pvc -l app=kafka
```

Check the log
```
kubectl get pods
kubectl logs -f {pod to be checked}
```