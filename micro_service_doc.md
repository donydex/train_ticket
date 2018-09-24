[toc]
# docker和k8s的相关两门网课，docker和k8s的相关指令
# github：https://github.com/microcosmx/train_ticket
# 云服务登陆方式:
## xshell
# 云服务器下载上传文件:
## filzezilla

# 基础集群部署：

## k8s部署
由于节点都在国内，所以直接用腾讯云提供的服务
参考文件：
train_ticket/Document/k8s/k8s install/k8s安装和部署流程.txt
train_ticket/Document/k8s/ReadMe.md


## 新的master节点步骤(一般都是加新的worker节点，不完全和master相同)：
-  yum intsall ntp
- ntpdate ntp.sjtu.edu.cn
------------时间同步--------------------------------------------
- kubectl apply -f kube-flannel.yml
------------配置网络-------------------------------------------
- yum install git -y
- git clone https://github.com/microcosmx/train_ticket.git
- yum -y install java-1.8.0-openjdk-devel
- wget http://apache.fayea.com/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
- tar -zxvf apache-maven-3.3.9-bin.tar.gz
- mv ./apache-maven-3.3.9 /usr/local/webserver/
- vi /etc/profile
- 在尾部追加行：
export MAVEN_HOME=/usr/local/webserver/apache-maven-3.3.9
export PATH=${MAVEN_HOME}/bin:${PATH}
- source /etc/profile
--------mvn 安装完成------------------
- sudo yum install epel-release -y
- sudo yum install -y python-pip -y
- sudo pip install docker-compose
----------docker-compose安装完成--
- docker-compose build
----------构建完镜像---------------------
- 卸载重装docker
为了保持和官方docker版本一致，需要重装docker
docker version 查看 若不为17.03需要重装
位置:
train_ticket/Document/k8s/k8s install/docker17.03/
yum install docker-ce-selinux-17.03.2.ce-1.el7.centos.noarch.rpm
yum install docker-ce-17.03.2.ce-1.el7.centos.x86_64.rpm
systemctl start docker.service
systemctl restart docker
--------若docker启动报错-------------
- docker 启动失败的解决方法
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- journalctl -xe 看运行报错
---------删除两个文件，留备份-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
- systemctl status docker 查看状态，active即为成功

----------docker重装完成-------------------------------
- vi /etc/hosts
所有节点都要配本地文件，否则会其他节点拉不到镜像，服务会均匀分布到其他节点上
- regserv的写过去
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
### 给生成的服务镜像打tag
- 把Docker Tag&Upload.md中的地址换成我们要的
```
%s/10.141.211.160:5000/regserv:5000/g
```
- 以下为已经改写好的
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
有五个基础镜像
```
mongo:latest
mysql:latest
rabbitmq:management
openzipkin/zipkin:latest
redis:latest
```
这五个基础镜像需要自己拉取
```
docker pull mongo:latest
docker pull mysql:latest
docker pull rabbitmq:management
docker pull openzipkin/zipkin:latest
docker pull redis:latest
```
### 配置私有仓库:
若未配置私有仓库，则需配置私有仓库，若已经配置完成，则跳过
私有仓库的设置:
https://blog.csdn.net/u010397369/article/details/42422243
vi /etc/docker/daemon.json
{
  "insecure-registries": ["regserv:5000"]
}
若要加速，为下列：
{
    "registry-mirrors": [
      "https://registry.docker-cn.com"
    ],
    "insecure-registries": ["regserv:5000"]
}

保存退出，再次重启docker
systemctl restart docker
否则会有:
上传镜像报错：server gave HTTP response to HTTPS client
### docker 启动失败的解决方法
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- yum install docker
- journalctl -xe 看运行报错
---------删除两个文件，留备份-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
删除了/var/lib/docker的话相当于所有镜像都删除了
- systemctl status docker 查看状态，active即为成功

### 验证私有仓库：
每个节点上都要配置好私有仓库：
测试：
```
docker pull regserv:5000/master/cluster-ts-travel2-service
```

### 推送镜像到远程的私有仓库:

将打好tag的镜像推到私有仓库：(本地仓库拉取速度远快于远程仓库)
同样的，需要修改
以下是修改完的版本:
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
### Deploy 到k8s集群里面:
先修改deployment文件:
把原ip:5000都修改为你的regserv:5000
然后要跑和文档同目录的不能跑在上级目录deploy文件混淆
train_ticket/Document/k8s/

kubectl apply -f ts-deployment-part1.yml
kubectl apply -f ts-deployment-part2.yml
kubectl apply -f ts-deployment-part3.yml

## 新的worker节点:
-  yum intsall ntp
- ntpdate ntp.sjtu.edu.cn
------------时间同步--------------------------------------------
- kubectl apply -f kube-flannel.yml
------------配置网络-------------------------------------------
- yum install git -y
- git clone https://github.com/microcosmx/train_ticket.git
### 配置私有仓库:
若未配置私有仓库，则需配置私有仓库，若已经配置完成，则跳过
私有仓库的设置:
https://blog.csdn.net/u010397369/article/details/42422243
vi /etc/docker/daemon.json
{
  "insecure-registries": ["regserv:5000"]
}
若要加速，为下列：
{
    "registry-mirrors": [
      "https://registry.docker-cn.com"
    ],
    "insecure-registries": ["regserv:5000"]
}

保存退出，再次重启docker
systemctl restart docker
否则会有:
上传镜像报错：server gave HTTP response to HTTPS client
### docker 启动失败的解决方法
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- yum install docker
- journalctl -xe 看运行报错
---------删除两个文件，留备份-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
删除了/var/lib/docker的话相当于所有镜像都删除了
- systemctl status docker 查看状态，active即为成功

### 验证私有仓库：
每个节点上都要配置好私有仓库：
测试：
```
docker pull regserv:5000/master/cluster-ts-travel2-service
```



- # 其他:批量删除docker镜像：
docker rmi $(docker images | grep "ts-" | awk '{print $3}') 
docker rm `docker ps -a | grep Exited | awk '{print $1}'` //删除异常停止的docker容器 

- # 其他:无法push到本地仓库：
解决之后呢，往regserv推，可能会遇到，regserv已经满了的情况，那就删空间


- # 服务只需要在master节点上跑，不用自所有节点上运行

- # 其他:k8s批量删除服务：
```
kubectl get pods | grep Evicted | awk '{print $1}' | xargs kubectl delete pod
第一个是获取pods信息，第二个是过滤与Evicted有关的,第三个是第一列，第四个是作为kubectl参数执行
所以：
kubectl get pods| awk '{print $1}'| xargs kubectl delete pods 
删除所有pods，无视状态
kubectl get deploy| awk '{print $1}'| xargs kubectl delete deploy 
删除所有deploy，无视状态
kubectl get svc| awk '{print $1}'| xargs kubectl delete svc 
删除所有service,无视状态
```
- 卸载重装docker
为了保持和官方docker版本一致，需要重装docker
docker version 查看 若不为17.03需要重装
位置:
train_ticket/Document/k8s/k8s install/docker17.03/
yum install docker-ce-selinux-17.03.2.ce-1.el7.centos.noarch.rpm
yum install docker-ce-17.03.2.ce-1.el7.centos.x86_64.rpm
systemctl start docker.service
systemctl restart docker
--------若docker启动报错-------------
- docker 启动失败的解决方法
https://blog.csdn.net/qq_35904833/article/details/74189383
https://serverfault.com/questions/780844/cant-start-docker-daemon-invalid-image
- journalctl -xe 看运行报错
---------删除两个文件，留备份-----------------------
- mv /var/run/docker.pid /var/run/docker_old.pid
- mv /var/lib/docker/ /var/lib/docker_old/
- systemctl status docker 查看状态，active即为成功

----------docker重装完成-------------------------------
- vi /etc/hosts
所有节点都要配本地文件，否则会其他节点拉不到镜像，服务会均匀分布到其他节点上
- regserv的写过去
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


- # 其他:kubectl apply 和 kubectl create的区别
不需要删除微服务之后再重新部署，修改文件后，重新输入kubectl apply 部署文件名.yml就可以更新
kubctl create需要删除后再重新create

- # 其他:centos的docker卸载和报错解决
centos卸载docker：https://www.jianshu.com/p/438f5fdc696b
centos安装文档里的docker报错：
Error: docker-ce-selinux conflicts with 2:container-selinux-2.12-2.gite7096ce.el7.noarch
https://github.com/docker/for-linux/issues/20
sudo yum erase docker-engine-selinux

- # 资源限制
可能有资源限制，worker-01一跑就卡，这个需要测试

- # mvn clean package 编译的错误，应该是编译的问题
1.0那个报错，到那个目录去改名字，改成jar包就可以了

- # WARNING: IPv4 forwarding is disabled. Networking will not work
docker-compose -f docker-compose.yml build
这一步遇到报错：
WARNING: IPv4 forwarding is disabled. Networking will not work
解决方案：
https://blog.csdn.net/weiguang1017/article/details/76212203

- # 修改maven的默认仓库
https://blog.csdn.net/timo1160139211/article/details/54289938
mvn -n 可以看到 mvn config文件夹的位置

- # master节点部署项目顺序
先mvn编译出jar包，然后再微服务化
mvn报错的时候仔细看报错信息，对应目录下应该有相应的jar包
mvn clean -e -X：Debug模式

- # 报错:consider setting COMPOSE_HTTP_TIMEOUT to a higher value (current value: 60).

https://stackoverflow.com/questions/36488209/how-to-override-the-default-value-of-compose-http-timeout-with-docker-compose-co

# k8s常用命令要掌握

# 目前问题:
三个节点上，大部分服务已启动，仍有一些未启动，需要解决
查看
kubectl get pod
kubectl describe pod pod.name
解决相应的问题

# 接下来的工作:
服务监控，获取相应的指标
找指标对服务重新划分
重构服务

# 知识
- docker
- kubectl
- springboot
- springcloud

# 任务分配

# 项目进度





