## Beckend parent Einkaufliste 
### Configure HTTPS For ekl-backend-ws-dev
#### Step 1 Create JKS
execute the steps described in [technical-tutorial.jenkins.configure-https](https://github.com/qiaohanscode/technical-tutorial/blob/main/jenkins/3-configure-https.md)
by replacing the dns.

#### Step 2 Create K8S Secret
```
kubectl create secret generic jks-ekl-backend-ws-dev \
--from-file=EKL_BACKEND_WS_JKS=ekl-backend-ws-dev.jks \
--type=Opague --dry-run=client -o yaml > secret-ekl-backend-ws-dev-jks.yaml

kubectl apply -f secret-ekl-backend-ws-dev-jks.yaml
```


