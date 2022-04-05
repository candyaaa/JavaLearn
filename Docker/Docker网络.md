# Docker网络

当项目大规模的使用Docker时，不可避免的会遇到Docker的网络通信问题，要解决Docker网络通信问题，我们有必要深入了解Docker的网络知识。

以下Docker的讲解分为三个部分：

- Docker的原理
- 理论的验证
- 附录
- 参考

## Docker的网络原理

Docker有三种开箱即用的网络模式，分别为：

- 桥接模式：使用`--network=bridge`指定，这种也是默认网络模式。桥接模式下，Docker会为新容器分配独立的网络命名空间，创建好`veth pair`，一端接入容器，一端接入`docker0网桥`。Docker会为每个容器自动分配好IP地址，默认范围是`172.17.0.0/24`,`docker0`默认地址为`172.17.0.1`,并且设置所有容器的网关均为`docker0`,这样所有接入网桥的容器均可以通过二层网络进行通讯。
- 主机模式：使用`--network=host`指定。主机模式下，不会为新容器分配独立的网络命名空间，这样容器一切的网络设备（如网卡、协议栈等）都将直接使用宿主机上的真实网络设备，容器也不会拥有自己独立的IP地址了。此模式下容器与外界通讯无需进行NAT转换，没有性能损耗，但是缺点也是十分明显，没有网络设备之间的隔离那么就无法避免网络资源之间的冲突，比如端口号就不允许重复。
- 空置模式：使用`--network=none`指定。空置模式下，Docker 会给新容器创建独立的网络名称空间，但是不会创建任何虚拟的网络设备，此时容器能看到的只有一个回环设备（Loopback Device）而已。提供这种方式是为了方便用户去做自定义的网络配置，如自己增加网络设备、自己管理 IP 地址，等等。

除了三种开箱即用的网络设备外，Docker还支持以下由用户自行创建的网络：

- 容器模式：创建容器后使用`--network=container:容器名称`指定。容器模式下，新创建的容器将会加入指定的容器的网络名称空间，共享一切的网络资源，但其他资源，如文件、`PID` 等默认仍然是隔离的。两个容器间可以直接使用回环地址（localhost）通信，当然端口号等网络资源不能有冲突。
- `MACVLAN`模式：使用`docker network create -d macvlan`创建，此网络允许为容器指定一个副本网卡，容器通过副本网卡的MAC地址来使用宿主机上的物理设备，在追求性能的场合，这种网络是最好的选择，Docker的MACVLAN只支持Bridge通信，因此在功能表现上与桥接模式相类似。
- `Overlay`模式：使用`docker network create -d overlay`创建，Docker说的`Overlay`网络实际上就是特指VXLAN，这种网络模式主要用于`Docker Swarm`服务之间的通讯，然而`Docker Swarm`终究败于`Kubernetes`，未能成为主流，所以这种网络模式实际上很少使用。

## 理论的验证

### 运行`Docker`查看网络的变化

- 运行`Docker`，使用`ip addr`查看网卡信息，比平常多了`docker0`网卡，观察信息，注意`docker0`
  - lo是环回接口
  - ens33本地网卡
  - `docker0`:虚拟网桥，state=DOWN、mac=02:42:cf:86:45:df、IPv4=172.17.0.1/16、IPv6=fe80::74b6:5a65:c82d:944e/64

```bash
[root@localhost ~]# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: ens33: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 00:0c:29:9d:bb:5e brd ff:ff:ff:ff:ff:ff
    inet 192.168.112.128/24 brd 192.168.112.255 scope global noprefixroute dynamic ens33
       valid_lft 1184sec preferred_lft 1184sec
    inet6 fe80::74b6:5a65:c82d:944e/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
3: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default 
    link/ether 02:42:cf:86:45:df brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
```

- 启动tomcat容器

```bash
docker run -itd -p 8080:8080 --name mytomcat tomcat
```

- 查看宿主机网卡网卡信息
  - `docker0`信息：state=UP
  - 新增`vethdda859b`虚拟网卡

```bash
[root@192 ~]# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: ens33: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 00:0c:29:9d:bb:5e brd ff:ff:ff:ff:ff:ff
    inet 192.168.112.128/24 brd 192.168.112.255 scope global noprefixroute dynamic ens33
       valid_lft 1654sec preferred_lft 1654sec
    inet6 fe80::74b6:5a65:c82d:944e/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
3: docker0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:cf:86:45:df brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
    inet6 fe80::42:cfff:fe86:45df/64 scope link 
       valid_lft forever preferred_lft forever
7: vethdda859b@if6: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue master docker0 state UP group default 
    link/ether ae:a7:dd:f6:da:03 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet6 fe80::aca7:ddff:fef6:da03/64 scope link 
       valid_lft forever preferred_lft forever
```

- 查看docker容器网卡信息
  - `lo`：环回接口
  - `eth0`：虚拟网卡

```bash
## ip addr命令找不到,安装iproute2
[root@192 ~]# docker exec -it mytomcat apt-get install -y iproute2
[root@192 ~]# docker exec -it mytomcat ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 655apt-get install -y iproute236 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
6: eth0@if7: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.17.0.2/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever
# netstat命令找不到，安装net-tools       
[root@192 ~]# docker exec -it mytomcat apt install net-tools
# 查看默认网关
[root@192 ~]# docker exec -it mytomcat netstat -rn
Kernel IP routing table
Destination     Gateway         Genmask         Flags   MSS Window  irtt Iface
0.0.0.0         172.17.0.1      0.0.0.0         UG        0 0          0 eth0
172.17.0.0      0.0.0.0         255.255.0.0     U         0 0          0 eth0
```

网卡`docker0`的state变成UP,并且新增`vethdda859b`网卡。查看默认网关为`172.17.0.1`，正是`docker0`的`IP`地址。

### 分析容器内和外网通讯流程

我们在容器和宿主机分别通过`tcpdump`进行抓包，并且在容器中对外网（`112.80.248.75`）发起`HTTP`请求，分析容器内和外网的网络通讯流程。

- 首先在容器中安装`tcpdump`

```bash
[root@192 ~]#apt-get install iputils-ping
```

- 容器中发送`HTTP`请求

```bash
[root@192 ~]# docker exec -it mytomcat curl www.baidu.com
```

- 查看容器`veth0`发送的网络数据包信息：

```bash
[root@192 ~]# docker exec -it mytomcat tcpdump -i eth0 -e src 172.17.0.2 and host 112.80.248.75 -n -vv
...
19:46:48.038415 02:42:ac:11:00:02 > 02:42:cf:86:45:df, ethertype IPv4 (0x0800), length 131: (tos 0x0, ttl 64, id 315, offset 0, flags [DF], proto TCP (6), length 117)
    172.17.0.2.45808 > 112.80.248.75.80: Flags [P.], cksum 0x1517 (incorrect -> 0xc39e), seq 0:77, ack 1, win 29200, length 77: HTTP, length: 77
	GET / HTTP/1.1
	Host: www.baidu.com
	User-Agent: curl/7.74.0
	Accept: */*
...
```

当我们从容器中向外网发送`HTTP`请求时，生成对应的数据包，提取我们所关注的信息：

```
源 MAC：veth0 的 MAC
目标MAC：网关的 MAC（即网桥的 MAC也就是docker0的MAC地址）
源 IP：veth0 的 IP，即 172.17.0.2
目标IP：外网IP
```

基于`veth pair`的原理，从`veth0`发出的数据包，将会从`veth1 `网络接口转发出来，发送到`docker0`，`docker0`网桥配置IP地址，将会触发`Linux Bridge`的特殊转发规则，这个数据包将不会转发给任何设备，而是转交给主机的协议栈处理。

注意，从这步以后就是三层路由了，已不在网桥的工作范围之内，是由` Linux `主机依靠 `Netfilter `进行 `IP` 转发（`IP Forward`）去实现的。

数据包经过主机协议栈，Netfilter 的钩子被激活，预置好的 `iptables NAT` 规则会修改数据包的源 IP 地址，将其改为物理网卡 `ens33`的 IP 地址，并在映射表中记录设备端口及两个 IP 地址之间的对应关系，经过 SNAT 之后的数据包，最终会从 `ens33` 出去。

- 使用`tcpdump`分析`ens33`发送的数据包：

```bash
[root@192 ~]# tcpdump -i ens33 -e dst 112.80.248.75 -n -vv
...
03:59:05.154530 00:0c:29:9d:bb:5e > 00:50:56:eb:dd:23, ethertype IPv4 (0x0800), length 131: (tos 0x0, ttl 63, id 59032, offset 0, flags [DF], proto TCP (6), length 117)
    192.168.112.128.45816 > 112.80.248.75.http: Flags [P.], cksum 0x9a2c (incorrect -> 0xf8b5), seq 0:77, ack 1, win 29200, length 77: HTTP, length: 77
	GET / HTTP/1.1
	Host: www.baidu.com
	User-Agent: curl/7.74.0
	Accept: */*
...
```

获取我们所关注的信息：

```
源 MAC：ens33 的 MAC，即 00:0c:29:9d:bb:5e
目标 MAC：下一跳（Hop）的 MAC，即 00:50:56:eb:dd:23
源 IP：ens33 的 IP，即 192.168.112.128
目标 IP：外网的 IP，即 112.80.248.75
```

可见，经过主机协议栈后，数据包的源和目标 IP 地址均为公网的 IP，这个数据包在外部网络中可以根据 IP 正确路由到目标服务器手上。当目标服务器处理完毕，对该请求发出响应后，返回数据包的目标地址也是公网 IP。当返回的数据包经过链路所有跳点，到达`ens33`网络接口，由`ens33`到达至`docker0`，会触发特殊转发规则，交由协议栈处理。此时 `Linux` 将根据映射表中的转换关系做 `DNAT` 转换，把目标 `IP` 地址从 `ens33` 替换回 `veth0` 的 `IP`，最终发往`veth0`

- 使用`tcpdump`分析`veth0`最终接收的数据包

```bash
[root@192 ~]# docker exec -it mytomcat tcpdump -i eth0 -e  tcp and dst 172.17.0.2  -n -vv
...
20:38:04.266572 02:42:cf:86:45:df > 02:42:ac:11:00:02, ethertype IPv4 (0x0800), length 2835: (tos 0x0, ttl 127, id 18446, offset 0, flags [none], proto TCP (6), length 2821)
    112.80.248.76.80 > 172.17.0.2.51150: Flags [P.], cksum 0x1fa8 (incorrect -> 0x5f33), seq 1:2782, ack 78, win 64240, length 2781: HTTP, length: 2781
	HTTP/1.1 200 OK
	Accept-Ranges: bytes
	Cache-Control: private, no-cache, no-store, proxy-revalidate, no-transform
	Connection: keep-alive
	Content-Length: 2381
	Content-Type: text/html
	Date: Sun, 20 Mar 2022 10:03:02 GMT
	Etag: "588604f8-94d"
	Last-Modified: Mon, 23 Jan 2017 13:28:24 GMT
	Pragma: no-cache
	Server: bfe/1.0.8.18
	Set-Cookie: BDORZ=27315; max-age=86400; domain=.baidu.com; path=/
	
	<!DOCTYPE html>
...
```

提取我们所关注的信息：

```
源 MAC：网桥的 MAC
目标 MAC：veth0 的 MAC
源 IP：外网的 IP，即 112.80.248.75
目标 IP：veth0 的 IP，即 172.17.0.2
```

至此容器与外网的网络交互分析完~

### link

#### link的介绍

`--link`是Docker遗留的功能，在之后的版本可能会被删除，建议使用自定义网络的方式进行容器间的网络通讯。虽然自定义网络不支持像`--link`在容器间共享环境变量。但是我们可以用其他机制更灵活的控制容器间共享环境变量。例如进行文件挂载。

**注意：`--link`的作用是单向的。**

#### link的原理

`--link`能使目标容器通过容器名称与源容器通过容器名称进行通信。分别通过两种方式将源容器的链接信息暴漏给目标容器：

	- 环境变量
	- 更新`etc/hosts`文件

#### hosts文件

分别启动两个tomcat容器，分别为tomcat-01、tomcat-02，并且tomcat-01链接到tomcat-02

```bash
$ docker run -itd -p 8080:8080 -v /etc/localtime:/etc/localtime --name tomcat-01 tomcat
$ docker exec -it tomcat-01 apt update
$ docker exec -it tomcat-01 apt-get install iputils-ping
$ docker run -itd -p 8081:8080 -v /etc/localtime:/etc/localtime --name tomcat-02 --link tomcat-01 tomcat
$ docker exec -it tomcat-02 apt update
$ docker exec -it tomcat-02 apt-get install iputils-ping
```

查看tomcat-02的`etc/hosts`文件，tomcat-02的hosts文件中有tomcat-01与IP地址的映射关系，这也是为什么tomcat-02与tomcat-01能使用容器名称通信的原因。

```bash
$ docker exec -it tomcat-02 ping tomcat-01
PING tomcat-01 (172.17.0.2) 56(84) bytes of data.
64 bytes from tomcat-01 (172.17.0.2): icmp_seq=1 ttl=64 time=0.150 ms
64 bytes from tomcat-01 (172.17.0.2): icmp_seq=2 ttl=64 time=0.080 ms
$ docker exec -it tomcat-02 cat /etc/hosts
127.0.0.1	localhost
::1	localhost ip6-localhost ip6-loopback
fe00::0	ip6-localnet
ff00::0	ip6-mcastprefix
ff02::1	ip6-allnodes
ff02::2	ip6-allrouters
172.17.0.2	tomcat-01 032d8267e12b
172.17.0.3	e9d62de11e27
```

- 环境变量

当我们使用`--link`链接容器时，Docker会根据`--link`参数自动在目标容器中创建环境变量，辅助他们之前的通讯。但是如果敏感数据存储在其中，这可能会产生严重的安全隐患。

在之前的例子中，我们通过tomcat-02	`--link`	tomcat-01，那么Docker将会自动在tomcat-02中创建对应的环境变量，如下所示：

```bash
$ docker exec -it tomcat-02 env
PATH=/usr/local/tomcat/bin:/usr/local/openjdk-11/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
HOSTNAME=e9d62de11e27
TERM=xterm
TOMCAT_01_PORT=tcp://172.17.0.2:8080
TOMCAT_01_PORT_8080_TCP=tcp://172.17.0.2:8080
TOMCAT_01_PORT_8080_TCP_ADDR=172.17.0.2
TOMCAT_01_PORT_8080_TCP_PORT=8080
TOMCAT_01_PORT_8080_TCP_PROTO=tcp
TOMCAT_01_NAME=/tomcat-02/tomcat-01
TOMCAT_01_ENV_JAVA_HOME=/usr/local/openjdk-11
TOMCAT_01_ENV_LANG=C.UTF-8
TOMCAT_01_ENV_JAVA_VERSION=11.0.14.1
TOMCAT_01_ENV_CATALINA_HOME=/usr/local/tomcat
TOMCAT_01_ENV_TOMCAT_NATIVE_LIBDIR=/usr/local/tomcat/native-jni-lib
TOMCAT_01_ENV_LD_LIBRARY_PATH=/usr/local/tomcat/native-jni-lib
TOMCAT_01_ENV_GPG_KEYS=A9C5DF4D22E99998D9875A5110C01C5A2F6059E7
TOMCAT_01_ENV_TOMCAT_MAJOR=10
TOMCAT_01_ENV_TOMCAT_VERSION=10.0.18
TOMCAT_01_ENV_TOMCAT_SHA512=a9e3c516676369bd9d52e768071898b0e07659a9ff03b9dc491e53f084b9981a929bf2c74a694f06ad26dae0644fb9617cc6e364f0e1dcd953c857978a95a644
JAVA_HOME=/usr/local/openjdk-11
LANG=C.UTF-8
JAVA_VERSION=11.0.14.1
CATALINA_HOME=/usr/local/tomcat
TOMCAT_NATIVE_LIBDIR=/usr/local/tomcat/native-jni-lib
LD_LIBRARY_PATH=/usr/local/tomcat/native-jni-lib
GPG_KEYS=A9C5DF4D22E99998D9875A5110C01C5A2F6059E7
TOMCAT_MAJOR=10
TOMCAT_VERSION=10.0.18
TOMCAT_SHA512=a9e3c516676369bd9d52e768071898b0e07659a9ff03b9dc491e53f084b9981a929bf2c74a694f06ad26dae0644fb9617cc6e364f0e1dcd953c857978a95a644
HOME=/root
```

### 自定义网络

#### 自定义网络的特性

创建自定义网络`mynet`

- 定义网络模式：`bridge`
- 子网为`192.168.254.0`
- 网关为`192.168.254.1`

```bash
$ docker network create --driver bridge --subnet 192.168.254.0/16 --gateway 192.168.254.1 mynet
$ ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: ens33: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 00:0c:29:9d:bb:5e brd ff:ff:ff:ff:ff:ff
    inet 192.168.112.133/24 brd 192.168.112.255 scope global noprefixroute dynamic ens33
       valid_lft 1653sec preferred_lft 1653sec
    inet6 fe80::74b6:5a65:c82d:944e/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
4: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default 
    link/ether 02:42:a7:4c:85:35 brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
5: br-f6af4f3be37a: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default 
    link/ether 02:42:31:1c:a2:66 brd ff:ff:ff:ff:ff:ff
    inet 192.168.254.1/16 brd 192.168.255.255 scope global br-f6af4f3be37a
       valid_lft forever preferred_lft forever
```

我们使用ip addr查看宿主机，多了`br-f6af4f3be37a`网卡。

分别运行tomcat03、tomcat04，指定mynet网络

```bash
$ docker run -itd -p 8083:8080 -v /etc/localtime:/etc/localtime --network mynet  --name  tomcat-03 tomcat
$ docker exec -it tomcat-03 apt update
$ docker exec -it tomcat-03 apt-get install iputils-ping -y
$ docker run -itd -p 8084:8080 -v /etc/localtime:/etc/localtime --network mynet  --name  tomcat-04 tomcat
$ docker exec -it tomcat-04 apt update
$ docker exec -it tomcat-04 apt-get install iputils-ping -y
```

直接通过容器名称进行ping

```bash
$ docker exec -it tomcat-03 ping tomcat-04
PING tomcat-04 (192.168.0.2) 56(84) bytes of data.
64 bytes from tomcat-04.mynet (192.168.0.2): icmp_seq=1 ttl=64 time=0.046 ms
64 bytes from tomcat-04.mynet (192.168.0.2): icmp_seq=2 ttl=64 time=0.084 ms
$ docker exec -it tomcat-04 ping tomcat-03
PING tomcat-03 (192.168.0.1) 56(84) bytes of data.
64 bytes from tomcat-03.mynet (192.168.0.1): icmp_seq=1 ttl=64 time=0.035 ms
64 bytes from tomcat-03.mynet (192.168.0.1): icmp_seq=2 ttl=64 time=0.083 ms

```

容器之间是互通的，我们可以理解相当于Docker帮我们维护了一个类似DNS的服务，每次使用自定义网络进行创建容器时，Docker会将容器名和IP的映射添加到DNS服务中，当我们通过容器请求时，会先到DNS服务中找到对应的IP然后进行请求。

#### 自定义网络容器和Docker的bridge网络网络的通讯

我们可以通过docker network connet [OPTIONS] NETWORK CONTAINER命令，将容器加入指定网络

```bash
$ docker network connect mynet tomcat-01
$ docker exec -it tomcat-01 ping tomcat-03
PING tomcat-03 (192.168.0.1) 56(84) bytes of data.
64 bytes from tomcat-03.mynet (192.168.0.1): icmp_seq=1 ttl=64 time=0.113 ms
64 bytes from tomcat-03.mynet (192.168.0.1): icmp_seq=2 ttl=64 time=0.054 ms
$ docker exec -it tomcat-01 ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
13: eth0@if14: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.17.0.2/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever
17: eth1@if18: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:c0:a8:00:03 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 192.168.0.3/16 brd 192.168.255.255 scope global eth1
       valid_lft forever preferred_lft forever
$ docker network inspect mynet
[
    {
        "Name": "mynet",
        "Id": "f6af4f3be37ae894abdf743eda8c44d869f173d6d4f4c9c1fde8d70ac16722b5",
        "Created": "2022-04-03T23:09:41.680599563+08:00",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    "Subnet": "192.168.254.0/16",
                    "Gateway": "192.168.254.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "032d8267e12bc3962d37da82b8d1d1bf794d1894e1dea3981c81736496b1e0c1": {
                "Name": "tomcat-01",
                "EndpointID": "c61c2b02e6a98d51240073c5c6e5c79675521e0a13de5129fcbb7e8edabd2c57",
                "MacAddress": "02:42:c0:a8:00:03",
                "IPv4Address": "192.168.0.3/16",
                "IPv6Address": ""
            },
            "0ac5f99aaac460dcad2a810c99c70105dd237ef166dbaebf182263f809e084a0": {
                "Name": "tomcat-04",
                "EndpointID": "03db64f520025cd311ee6b8be12704871765dfb017d1e9975021c8bd584f2d81",
                "MacAddress": "02:42:c0:a8:00:02",
                "IPv4Address": "192.168.0.2/16",
                "IPv6Address": ""
            },
            "4a7c7daa4716ade6974be77e01691f18f7fd9fc5898ab059c12a23a4a47d525b": {
                "Name": "tomcat-03",
                "EndpointID": "17b4f3d7f56dbf9c14e4cca3477c9874f2ee2595fa2f4c9051782414d15ca24f",
                "MacAddress": "02:42:c0:a8:00:01",
                "IPv4Address": "192.168.0.1/16",
                "IPv6Address": ""
            }
        },
        "Options": {},
        "Labels": {}
    }
]
```

我们可以看到Docker通过一台宿主机添加多个网卡设备的方式，来实现不同网络之间容器的网络通讯。

使用docker network inspect mynet也发现现在mynet网络中存在三个容器了。

### 遇到的一些问题

- 执行apt-update，报错

```bash
Err:1 http://security.debian.org/debian-security buster/updates InRelease
  Temporary failure resolving 'security.debian.org'
Err:2 http://deb.debian.org/debian buster InRelease                  
  Temporary failure resolving 'deb.debian.org'
Err:3 http://deb.debian.org/debian buster-updates InRelease
  Temporary failure resolving 'deb.debian.org'
Reading package lists... Done    
W: Failed to fetch http://deb.debian.org/debian/dists/buster/InRelease  Temporary failure resolving 'deb.debian.org'
W: Failed to fetch http://security.debian.org/debian-security/dists/buster/updates/InRelease  Temporary failure resolving 'security.debian.org'
W: Failed to fetch http://deb.debian.org/debian/dists/buster-updates/InRelease  Temporary failure resolving 'deb.debian.org'
W: Some index files failed to download. They have been ignored, or old ones used instead.
```

这是因为网络的原因，我们可以通过指定DNS服务器

在`/etc/docker/daemon.json`中加入

```json
{
  "dns": ["8.8.8.8", "8.8.4.4"]
}
```

重启docker服务

- apt-get update安装软件包时出现

 ```bash
Release file for xxx is not valid yet (invalid for another 159d 15h 21min 32s). Updates for this repository will not be applied.
 ```

每个存储库文件都在某个日期签名，由于我本机的机器的时间已经过去了，所以本地机器报错。更新本地机器时间就好了

- centos更新时间

安装ntpdate工具

```bash
# yum -y install ntp ntpdate
```

设置系统时间与网络时间同步

```bash
# ntpdate cn.pool.ntp.org
```

将系统时间写入硬件时间

```bash
# hwclock --systohc
```

- 将主机中的时间同步到Docker中，直接将/etc/localtime文件挂载到容器中就好了

```
-v /etc/localtime:/etc/localtime
```

- 通过apt-get install 安装文件出现

```bash
E: Unable to locate package iproute2
```

当您使用 install 命令时，apt 包管理器会搜索缓存以获取包和版本信息，然后通过网络从其存储库下载它。如果软件包不在此缓存中，您的系统将无法安装它。当你有一个新安装的 Ubuntu 系统时，缓存是空的。这就是为什么您应该在安装 Ubuntu 或任何其他基于 Ubuntu 的发行版（如 Linux Mint）后立即运行 apt update 命令。即使不是全新安装，您的 apt 缓存也可能已过时。更新它总是一个好主意。























