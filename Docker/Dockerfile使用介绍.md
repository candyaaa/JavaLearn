# Dockerfile

## Dockerfile介绍

dockerfile是一个文本文件，它包含组装Docker images的说明，当我们使用`docker build`命令，并且通过`--file`(可简写为`-f`)指定Dockerfile，Docker
将会读取指定的文件，并且通过这个文件创建一个镜像。

> 当不指定时，Docker默认找当前目录下的Dockerfile文件

## Dockerfile示例

```
# 1、第一行必须指定 基础镜像信息
FROM ubuntu
 
# 2、维护者信息
MAINTAINER docker_user docker_user@email.com
 
# 3、镜像操作指令
RUN echo "deb http://archive.ubuntu.com/ubuntu/ raring main universe" >> /etc/apt/sources.list
RUN apt-get update && apt-get install -y nginx
RUN echo "\ndaemon off;" >> /etc/nginx/nginx.conf
 
# 4、容器启动执行指令
CMD /usr/sbin/nginx
```

Dockerfile分为4个部分：
 - 基础镜像
 - 维护者信息
 - 镜像操作命令
 - 镜像启动命令

一开始必须要指明所基于的镜像名称，接下来一般会说明维护者信息；后面则是镜像操作指令，例如 RUN 指令。每执行一条RUN 指令，镜像添加新的一层，并提交；最后是 CMD 指令，来指明运行容器时的操作命令。

## 构建镜像

docker build 命令会根据 Dockerfile 文件及上下文构建新 Docker 镜像。构建上下文是指 Dockerfile 所在的本地路径或一个URL（Git仓库地址）。

将当前目录做为构建上下文时，可以像下面这样使用`docker build`命令构建镜像：
```
docker build .
```
说明：构建会在 Docker 后台守护进程（daemon）中执行，而不是CLI中。构建前，构建进程会将全部内容发送到守护进程。大多情况下，应该将一个空目录作为构建上下文环境，并将 Dockerfile 文件放在该目录下。

在 Docker 守护进程执行 Dockerfile 中的指令前，首先会对 Dockerfile 进行语法检查，有语法错误时会返回：

```
docker build -t nginx/v3 .
Sending build context to Docker daemon 2.048 kB
Error response from daemon: Unknown instruction: RUNCMD
```

在构建上下文中使用的 Dockerfile 文件，是一个构建指令文件。为了提高构建性能，可以通过.dockerignore文件排除上下文目录下不需要的文件和目录。

在 Docker 构建镜像的第一步，docker CLI 会先在上下文目录中寻找.dockerignore文件，根据.dockerignore 文件排除上下文目录中的部分文件和目录，然后把剩下的文件和目录传递给 Docker 服务。

## 镜像标签
构建时，还可以通过-t参数指定构建成镜像的仓库、标签。
```
docker build -t nginx/v3 .
```
如果存在多个仓库下，或使用多个镜像标签，就可以使用多个-t参数：

docker build -t nginx/v3:1.0.2 -t nginx/v3:latest .

## 缓存机制介绍

Docker守护进程会一条一条的执行Dockerfile中的指令，而且会在每一条指令提交之后生成新的镜像，最后会输出最终镜像的ID。生成完成之后，
Docker守护进程会自动清理你发送的上下文。Dockerfile文件中的每条指令会被独立执行，并创建一个新镜像。

Docker Daemon 通过 Dockerfile 构建镜像时，当发现即将新构建出的镜像与已有的某镜像重复时，可以选择放弃构建新的镜像，而是选用已有的镜像作为构建结果，也就是采取本地已经 cache 的镜像作为结果。 不需要进行重新构建镜像，如果我们希望在构建镜像时不使用缓存，可以在 `docker build` 命令中加上 `–no-cache` 参数。如果我们改变 Dockerfile 指令的执行顺序，或者修改或添加指令，都会使缓存失效。
Dockerfile 在执行的时候，当有执行过相同的代码并且顺序也一致的情况下，就会使用缓存镜像层进行构建新的镜像。

## cache机制实现原理

Docker镜像，由镜像层文件系统和镜像文件json文件组成，而这两者都含有一个同一个镜像ID。

遍历本地所有镜像，发现镜像与即将构建出的镜像一致时，将找到的镜像作为 cache 镜像，复用 cache 镜像作为构建结果。

## cache机制注意事项

cache 机制很大程度上做到了镜像的复用，降低存储空间的同时，还大大缩短了构建时间。然而，不得不说的是，想要用好 cache 机制，那就必须了解利用 cache 机制时的一些注意事项。

- ADD 命令与 COPY 命令：Dockerfile 没有发生任何改变，但是命令ADD run.sh / 中 Dockerfile 当前目录下的 run.sh 却发生了变化，从而将直接导致镜像层文件系统内容的更新，原则上不应该再使用 cache。那么，判断 ADD 命令或者 COPY 命令后紧接的文件是否发生变化，则成为是否延用 cache 的重要依据。Docker 采取的策略是：获取 Dockerfile 下内容（包括文件的部分 inode 信息），计算出一个唯一的 hash 值，若 hash 值未发生变化，则可以认为文件内容没有发生变化，可以使用 cache 机制；反之亦然。
- RUN 命令存在外部依赖：一旦 RUN 命令存在外部依赖，如RUN apt-get update，那么随着时间的推移，基于同一个基础镜像，一年的 apt-get update 和一年后的 apt-get update， 由于软件源软件的更新，从而导致产生的镜像理论上应该不同。如果继续使用 cache 机制，将存在不满足用户需求的情况。Docker 一开始的设计既考虑了外部依赖的问题，用户可以使用参数 --no-cache 确保获取最新的外部依赖

## 参考

http://open.daocloud.io/docker-build-de-cache-ji-zhi/