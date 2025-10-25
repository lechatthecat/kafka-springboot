## How to start this project
1. ./gradlew bootJar 
2. docker build -t kafka-springboot-test:latest -f docker/springboot/Dockerfile .
3. minikube image load kafka-springboot-test:latest
4. 
```
minikube start  
kubectl apply -f ./k8s/mysql-deployment.yaml 
kubectl apply -f ./k8s/springboot-deployment.yaml  
kubectl apply -f ./k8s/redis-configmap.yaml
kubectl apply -f ./k8s/redis-service.yaml
kubectl apply -f ./k8s/redis-statefulset.yaml
```
1. Check the pods
```
kubectl get pods
kubectl logs -f deployment/springboot
```
1. Check the URL
minikube service springboot --url
http://192.168.49.2:31415
1. Open the following url to see if it works
http://192.168.49.2:30885/hello

Now run the following to create the cluster:
kubectl exec -it redis-cluster-0 -- bash

redis-cli --cluster create \
  redis-cluster-0.redis-cluster:6379 \
  redis-cluster-1.redis-cluster:6379 \
  redis-cluster-2.redis-cluster:6379 \
  redis-cluster-3.redis-cluster:6379 \
  redis-cluster-4.redis-cluster:6379 \
  redis-cluster-5.redis-cluster:6379 \
  --cluster-replicas 1

kubectl exec -it redis-cluster-0 -- bash
redis-cli -c -h redis-cluster-0.redis-cluster -p 6379 cluster info

## Optional
delete the deployments
```
kubectl delete -f ./k8s/springboot-deployment.yaml
kubectl delete -f ./k8s/redis-statefulset.yaml
kubectl delete -f ./k8s/redis-service.yaml
kubectl delete -f ./k8s/redis-configmap.yaml
kubectl delete -f ./k8s/mysql-deployment.yaml
```

Check the log
```
kubectl get pods
kubectl logs -f {pod to be checked}
```