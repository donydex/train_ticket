[toc]
# docker��k8s������������Σ�docker��k8s�����ָ��
# github��https://github.com/microcosmx/train_ticket
# �Ʒ����½��ʽ:
## xshell
# �Ʒ����������ϴ��ļ�:
## filzezilla

# ������Ⱥ����

## k8s����
���ڽڵ㶼�ڹ��ڣ�����ֱ������Ѷ���ṩ�ķ���
�ο��ļ���
train_ticket/Document/k8s/k8s install/k8s��װ�Ͳ�������.txt
train_ticket/Document/k8s/ReadMe.md


## �µ�master�ڵ㲽��(һ�㶼�Ǽ��µ�worker�ڵ㣬����ȫ��master��ͬ)��
-  yum intsall ntp
- ntpdate ntp.sjtu.edu.cn
------------ʱ��ͬ��--------------------------------------------
- kubectl apply -f kube-flannel.yml
------------��������-------------------------------------------
- yum install git -y
- git clone https://github.com/microcosmx/train_ticket.git
- yum -y install java-1.8.0-openjdk-devel
- wget http://apache.fayea.com/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
- tar -zxvf apache-maven-3.3.9-bin.tar.gz
- mv ./apache-maven-3.3.9 /usr/local/webserver/
- vi /etc/profile
- ��β��׷���У�
export MAVEN_HOME=/usr/local/webserver/apache-maven-3.3.9
export PATH=${MAVEN_HOME}/bin:${PATH}
- source /etc/profile
--------mvn ��װ���------------------
- sudo yum install epel-release -y
- sudo yum install -y python-pip -y
- sudo pip install docker-compose
----------docker-compose��װ���--
- docker-compose build
----------�����꾵��---------------------
- ж����װdocker
Ϊ�˱��ֺ͹ٷ�docker�汾һ�£���Ҫ��װdocker
docker version �鿴 ����Ϊ17.03��Ҫ��װ
λ��:
train_ticket/Document/k8s/k8s install/docker17.03/
yum install docker-ce-selinux-17.03.2.ce-1.el7.centos.noarch.rpm
yum install docker-ce-17.03.2.ce-1.el7.centos.x86_64.rpm
systemctl start docker.service
systemctl restart docker
--------��docker��������-------------
- docker ����ʧ�ܵĽ������
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- journalctl -xe �����б���
---------ɾ�������ļ���������-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
- systemctl status docker �鿴״̬��active��Ϊ�ɹ�

----------docker��װ���-------------------------------
- vi /etc/hosts
���нڵ㶼Ҫ�䱾���ļ�������������ڵ����������񣬷������ȷֲ��������ڵ���
- regserv��д��ȥ
/etc/hosts:
```
172.17.0.9 regserv
172.17.0.3 manager-01
172.17.0.15 manager-02
172.17.0.14 manager-03
172.17.0.12 worker-01
172.17.0.13 worker-02
172.17.0.2 worker-03
172.17.0.16 worker-04
172.17.0.10 worker-05
172.17.0.6 worker-06
172.17.0.5 worker-07
172.17.0.4 worker-08
172.17.0.17 worker-09
172.17.0.7 worker-10
```
### �����ɵķ������tag
- ��Docker Tag&Upload.md�еĵ�ַ��������Ҫ��
```
%s/10.141.211.160:5000/regserv:5000/g
```
- ����Ϊ�Ѿ���д�õ�
```
docker tag ts/ts-admin-basic-info-service regserv:5000/master/cluster-ts-admin-basic-info-service
docker tag ts/ts-admin-order-service regserv:5000/master/cluster-ts-admin-order-service
docker tag ts/ts-admin-route-service regserv:5000/master/cluster-ts-admin-route-service
docker tag ts/ts-admin-travel-service regserv:5000/master/cluster-ts-admin-travel-service
docker tag ts/ts-admin-user-service regserv:5000/master/cluster-ts-admin-user-service
docker tag ts/ts-assurance-service regserv:5000/master/cluster-ts-assurance-service
docker tag ts/ts-basic-service regserv:5000/master/cluster-ts-basic-service
docker tag ts/ts-cancel-service regserv:5000/master/cluster-ts-cancel-service
docker tag ts/ts-config-service regserv:5000/master/cluster-ts-config-service
docker tag ts/ts-consign-price-service regserv:5000/master/cluster-ts-consign-price-service
docker tag ts/ts-consign-service regserv:5000/master/cluster-ts-consign-service
docker tag ts/ts-contacts-service regserv:5000/master/cluster-ts-contacts-service
docker tag ts/ts-execute-service regserv:5000/master/cluster-ts-execute-service
docker tag ts/ts-food-map-service regserv:5000/master/cluster-ts-food-map-service
docker tag ts/ts-food-service regserv:5000/master/cluster-ts-food-service
docker tag ts/ts-inside-payment-service regserv:5000/master/cluster-ts-inside-payment-service
docker tag ts/ts-login-service regserv:5000/master/cluster-ts-login-service
docker tag ts/ts-news-service regserv:5000/master/cluster-ts-news-service
docker tag ts/ts-notification-service regserv:5000/master/cluster-ts-notification-service
docker tag ts/ts-order-other-service regserv:5000/master/cluster-ts-order-other-service
docker tag ts/ts-order-service regserv:5000/master/cluster-ts-order-service
docker tag ts/ts-payment-service regserv:5000/master/cluster-ts-payment-service
docker tag ts/ts-preserve-other-service regserv:5000/master/cluster-ts-preserve-other-service
docker tag ts/ts-preserve-service regserv:5000/master/cluster-ts-preserve-service
docker tag ts/ts-price-service regserv:5000/master/cluster-ts-price-service
docker tag ts/ts-rebook-service regserv:5000/master/cluster-ts-rebook-service
docker tag ts/ts-register-service regserv:5000/master/cluster-ts-register-service
docker tag ts/ts-route-plan-service regserv:5000/master/cluster-ts-route-plan-service
docker tag ts/ts-route-service regserv:5000/master/cluster-ts-route-service
docker tag ts/ts-seat-service regserv:5000/master/cluster-ts-seat-service
docker tag ts/ts-security-service regserv:5000/master/cluster-ts-security-service
docker tag ts/ts-sso-service regserv:5000/master/cluster-ts-sso-service
docker tag ts/ts-station-service regserv:5000/master/cluster-ts-station-service
docker tag ts/ts-ticket-office-service regserv:5000/master/cluster-ts-ticket-office-service
docker tag ts/ts-ticketinfo-service regserv:5000/master/cluster-ts-ticketinfo-service
docker tag ts/ts-train-service regserv:5000/master/cluster-ts-train-service
docker tag ts/ts-travel2-service regserv:5000/master/cluster-ts-travel2-service
docker tag ts/ts-travel-service regserv:5000/master/cluster-ts-travel-service
docker tag ts/ts-travel-plan-service regserv:5000/master/cluster-ts-travel-plan-service
docker tag ts/ts-ui-dashboard regserv:5000/master/cluster-ts-ui-dashboard
docker tag ts/ts-verification-code-service regserv:5000/master/cluster-ts-verification-code-service
docker tag ts/ts-voucher-service regserv:5000/master/cluster-ts-voucher-service
docker tag mongo regserv:5000/master/cluster-ts-mongo
docker tag mysql regserv:5000/master/cluster-ts-mysql
docker tag rabbitmq:management regserv:5000/master/cluster-ts-rabbitmq-management
docker tag redis regserv:5000/master/cluster-ts-redis
docker tag openzipkin/zipkin regserv:5000/master/cluster-ts-openzipkin-zipkin
```
�������������
```
mongo:latest
mysql:latest
rabbitmq:management
openzipkin/zipkin:latest
redis:latest
```
���������������Ҫ�Լ���ȡ
```
docker pull mongo:latest
docker pull mysql:latest
docker pull rabbitmq:management
docker pull openzipkin/zipkin:latest
docker pull redis:latest
```
### ����˽�вֿ�:
��δ����˽�вֿ⣬��������˽�вֿ⣬���Ѿ�������ɣ�������
˽�вֿ������:
https://blog.csdn.net/u010397369/article/details/42422243
vi /etc/docker/daemon.json
{
  "insecure-registries": ["regserv:5000"]
}
��Ҫ���٣�Ϊ���У�
{
    "registry-mirrors": [
      "https://registry.docker-cn.com"
    ],
    "insecure-registries": ["regserv:5000"]
}

�����˳����ٴ�����docker
systemctl restart docker
�������:
�ϴ����񱨴�server gave HTTP response to HTTPS client
### docker ����ʧ�ܵĽ������
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- yum install docker
- journalctl -xe �����б���
---------ɾ�������ļ���������-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
ɾ����/var/lib/docker�Ļ��൱�����о���ɾ����
- systemctl status docker �鿴״̬��active��Ϊ�ɹ�

### ��֤˽�вֿ⣺
ÿ���ڵ��϶�Ҫ���ú�˽�вֿ⣺
���ԣ�
```
docker pull regserv:5000/master/cluster-ts-travel2-service
```

### ���;���Զ�̵�˽�вֿ�:

�����tag�ľ����Ƶ�˽�вֿ⣺(���زֿ���ȡ�ٶ�Զ����Զ�ֿ̲�)
ͬ���ģ���Ҫ�޸�
�������޸���İ汾:
```
docker push regserv:5000/master/cluster-ts-admin-basic-info-service
docker push regserv:5000/master/cluster-ts-admin-order-service
docker push regserv:5000/master/cluster-ts-admin-route-service
docker push regserv:5000/master/cluster-ts-admin-travel-service
docker push regserv:5000/master/cluster-ts-admin-user-service
docker push regserv:5000/master/cluster-ts-assurance-service
docker push regserv:5000/master/cluster-ts-basic-service
docker push regserv:5000/master/cluster-ts-cancel-service
docker push regserv:5000/master/cluster-ts-config-service
docker push regserv:5000/master/cluster-ts-consign-price-service
docker push regserv:5000/master/cluster-ts-consign-service
docker push regserv:5000/master/cluster-ts-contacts-service
docker push regserv:5000/master/cluster-ts-execute-service
docker push regserv:5000/master/cluster-ts-food-map-service
docker push regserv:5000/master/cluster-ts-food-service
docker push regserv:5000/master/cluster-ts-inside-payment-service
docker push regserv:5000/master/cluster-ts-login-service
docker push regserv:5000/master/cluster-ts-news-service
docker push regserv:5000/master/cluster-ts-notification-service
docker push regserv:5000/master/cluster-ts-order-other-service
docker push regserv:5000/master/cluster-ts-order-service
docker push regserv:5000/master/cluster-ts-payment-service
docker push regserv:5000/master/cluster-ts-preserve-other-service
docker push regserv:5000/master/cluster-ts-preserve-service
docker push regserv:5000/master/cluster-ts-price-service
docker push regserv:5000/master/cluster-ts-rebook-service
docker push regserv:5000/master/cluster-ts-register-service
docker push regserv:5000/master/cluster-ts-route-plan-service
docker push regserv:5000/master/cluster-ts-route-service
docker push regserv:5000/master/cluster-ts-seat-service
docker push regserv:5000/master/cluster-ts-security-service
docker push regserv:5000/master/cluster-ts-sso-service
docker push regserv:5000/master/cluster-ts-station-service
docker push regserv:5000/master/cluster-ts-ticket-office-service
docker push regserv:5000/master/cluster-ts-ticketinfo-service
docker push regserv:5000/master/cluster-ts-train-service
docker push regserv:5000/master/cluster-ts-travel2-service
docker push regserv:5000/master/cluster-ts-travel-service
docker push regserv:5000/master/cluster-ts-travel-plan-service
docker push regserv:5000/master/cluster-ts-ui-dashboard
docker push regserv:5000/master/cluster-ts-verification-code-service
docker push regserv:5000/master/cluster-ts-voucher-service
docker push regserv:5000/master/cluster-ts-mongo
docker push regserv:5000/master/cluster-ts-mysql
docker push regserv:5000/master/cluster-ts-rabbitmq-management
docker push regserv:5000/master/cluster-ts-redis
docker push regserv:5000/master/cluster-ts-openzipkin-zipkin
```
### Deploy ��k8s��Ⱥ����:
���޸�deployment�ļ�:
��ԭip:5000���޸�Ϊ���regserv:5000
Ȼ��Ҫ�ܺ��ĵ�ͬĿ¼�Ĳ��������ϼ�Ŀ¼deploy�ļ�����
train_ticket/Document/k8s/

kubectl apply -f ts-deployment-part1.yml
kubectl apply -f ts-deployment-part2.yml
kubectl apply -f ts-deployment-part3.yml

## �µ�worker�ڵ�:
-  yum intsall ntp
- ntpdate ntp.sjtu.edu.cn
------------ʱ��ͬ��--------------------------------------------
- kubectl apply -f kube-flannel.yml
------------��������-------------------------------------------
- yum install git -y
- git clone https://github.com/microcosmx/train_ticket.git
### ����˽�вֿ�:
��δ����˽�вֿ⣬��������˽�вֿ⣬���Ѿ�������ɣ�������
˽�вֿ������:
https://blog.csdn.net/u010397369/article/details/42422243
vi /etc/docker/daemon.json
{
  "insecure-registries": ["regserv:5000"]
}
��Ҫ���٣�Ϊ���У�
{
    "registry-mirrors": [
      "https://registry.docker-cn.com"
    ],
    "insecure-registries": ["regserv:5000"]
}

�����˳����ٴ�����docker
systemctl restart docker
�������:
�ϴ����񱨴�server gave HTTP response to HTTPS client
### docker ����ʧ�ܵĽ������
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- yum install docker
- journalctl -xe �����б���
---------ɾ�������ļ���������-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
ɾ����/var/lib/docker�Ļ��൱�����о���ɾ����
- systemctl status docker �鿴״̬��active��Ϊ�ɹ�

### ��֤˽�вֿ⣺
ÿ���ڵ��϶�Ҫ���ú�˽�вֿ⣺
���ԣ�
```
docker pull regserv:5000/master/cluster-ts-travel2-service
```



- # ����:����ɾ��docker����
docker rmi $(docker images | grep "ts-" | awk '{print $3}') 
docker rm `docker ps -a | grep Exited | awk '{print $1}'` //ɾ���쳣ֹͣ��docker���� 

- # ����:�޷�push�����زֿ⣺
���֮���أ���regserv�ƣ����ܻ�������regserv�Ѿ����˵�������Ǿ�ɾ�ռ�


- # ����ֻ��Ҫ��master�ڵ����ܣ����������нڵ�������

- # ����:k8s����ɾ������
```
kubectl get pods | grep Evicted | awk '{print $1}' | xargs kubectl delete pod
��һ���ǻ�ȡpods��Ϣ���ڶ����ǹ�����Evicted�йص�,�������ǵ�һ�У����ĸ�����Ϊkubectl����ִ��
���ԣ�
kubectl get pods| awk '{print $1}'| xargs kubectl delete pods 
ɾ������pods������״̬
kubectl get deploy| awk '{print $1}'| xargs kubectl delete deploy 
ɾ������deploy������״̬
kubectl get svc| awk '{print $1}'| xargs kubectl delete svc 
ɾ������service,����״̬
```
- ж����װdocker
Ϊ�˱��ֺ͹ٷ�docker�汾һ�£���Ҫ��װdocker
docker version �鿴 ����Ϊ17.03��Ҫ��װ
λ��:
train_ticket/Document/k8s/k8s install/docker17.03/
yum install docker-ce-selinux-17.03.2.ce-1.el7.centos.noarch.rpm
yum install docker-ce-17.03.2.ce-1.el7.centos.x86_64.rpm
systemctl start docker.service
systemctl restart docker
--------��docker��������-------------
- docker ����ʧ�ܵĽ������
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- journalctl -xe �����б���
---------ɾ�������ļ���������-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
- systemctl status docker �鿴״̬��active��Ϊ�ɹ�

----------docker��װ���-------------------------------
- vi /etc/hosts
���нڵ㶼Ҫ�䱾���ļ�������������ڵ����������񣬷������ȷֲ��������ڵ���
- regserv��д��ȥ
/etc/hosts:
```
172.17.0.9 regserv
172.17.0.3 manager-01
172.17.0.15 manager-02
172.17.0.14 manager-03
172.17.0.12 worker-01
172.17.0.13 worker-02
172.17.0.2 worker-03
172.17.0.16 worker-04
172.17.0.10 worker-05
172.17.0.6 worker-06
172.17.0.5 worker-07
172.17.0.4 worker-08
172.17.0.17 worker-09
172.17.0.7 worker-10
```


- # ����:kubectl apply �� kubectl create������
����Ҫɾ��΢����֮�������²����޸��ļ�����������kubectl apply �����ļ���.yml�Ϳ��Ը���
kubctl create��Ҫɾ����������create

- # ����:centos��dockerж�غͱ�����
centosж��docker��https://www.jianshu.com/p/438f5fdc696b
centos��װ�ĵ����docker����
Error: docker-ce-selinux conflicts with 2:container-selinux-2.12-2.gite7096ce.el7.noarch
https://github.com/docker/for-linux/issues/20
sudo yum erase docker-engine-selinux

- # ��Դ����
��������Դ���ƣ�worker-01һ�ܾͿ��������Ҫ����

- # mvn clean package ����Ĵ���Ӧ���Ǳ��������
1.0�Ǹ��������Ǹ�Ŀ¼ȥ�����֣��ĳ�jar���Ϳ�����

- # WARNING: IPv4 forwarding is disabled. Networking will not work
docker-compose -f docker-compose.yml build
��һ����������
WARNING: IPv4 forwarding is disabled. Networking will not work
���������
https://blog.csdn.net/weiguang1017/article/details/76212203

- # �޸�maven��Ĭ�ϲֿ�
https://blog.csdn.net/timo1160139211/article/details/54289938
mvn -n ���Կ��� mvn config�ļ��е�λ��

- # master�ڵ㲿����Ŀ˳��
��mvn�����jar����Ȼ����΢����
mvn�����ʱ����ϸ��������Ϣ����ӦĿ¼��Ӧ������Ӧ��jar��
mvn clean -e -X��Debugģʽ

- # ����:consider setting COMPOSE_HTTP_TIMEOUT to a higher value (current value: 60).

https://stackoverflow.com/questions/36488209/how-to-override-the-default-value-of-compose-http-timeout-with-docker-compose-co

# k8s��������Ҫ����

# Ŀǰ����:
�����ڵ��ϣ��󲿷ַ���������������һЩδ��������Ҫ���
�鿴
kubectl get pod
kubectl describe pod pod.name
�����Ӧ������

# �������Ĺ���:
�����أ���ȡ��Ӧ��ָ��
��ָ��Է������»���
�ع�����

# ֪ʶ
- docker
- kubectl
- springboot
- springcloud

# �������

# ��Ŀ����





