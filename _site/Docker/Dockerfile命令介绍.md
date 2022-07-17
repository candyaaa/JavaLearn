# Dockerfile命令介绍

## FROM

`From`为后续指定初始化一个基本镜像,它可以是任何有效的镜像，一个有效的`Dockerfile`文件必须以From开头。

```
FROM [--platform=<platform>] <image> [AS <name>]
```
or
```
FROM [--platform=<platform>] <image>[:<tag>] [AS <name>]
```
or
```
FROM [--platform=<platform>] <image>[@<digest>] [AS <name>]
```
## RUN

在镜像的构建过程中，并生成一个中间镜像。格式:

```
# shell格式
RUN <command>
#exec格式
RUN ["executable", "param1", "param2"]
```
- RUN 命令将在当前 image 中执行任意合法命令并提交执行结果。命令执行提交后，就会自动执行 Dockerfile 中的下一个指令。
- 层级 RUN 指令和生成提交是符合 Docker 核心理念的做法。它允许像版本控制那样，在任意一个点，对 image 镜像进行定制化构建。
- RUN 指令创建的中间镜像会被缓存，并会在下次构建中使用。如果不想使用这些缓存镜像，可以在构建时指定 --no-cache 参数，如：docker build --no-cache。

## COPY

格式:
```
COPY <源路径> ... <目标路径>
COPY ["<源路径>",... "<目标路径>"]
```
和 RUN 指令一样，也有两种格式，一种类似于命令行，一种类似于函数调用。COPY 指令将从构建上下文目录中 <源路径> 的文件/目录复制到新的一层的镜像内的`<目标路径>`位置。比如：

```
COPY hom* /mydir/
COPY hom?.txt /mydir/
```
<目标路径>可以是容器内的绝对路径，也可以是相对于工作目录的相对路径（工作目录可以用 WORKDIR 指令来指定）。目标路径不需要事先创建，如果目录不存在会在复制文件前先行创建缺失目录。

此外，还需要注意一点，使用 COPY 指令，源文件的各种元数据都会保留。比如读、写、执行权限、文件变更时间等。这个特性对于镜像定制很有用。特别是构建相关文件都在使用 Git 进行管理的时候。

## ADD

ADD 指令和 COPY 的格式和性质基本一致。但是在 COPY 基础上增加了一些功能。比如<源路径>可以是一个 URL，这种情况下，Docker 引擎会试图去下载这个链接的文件放到<目标路径>去。

在构建镜像时，复制上下文中的文件到镜像内，格式：
```
ADD <源路径>... <目标路径>
ADD ["<源路径>",... "<目标路径>"]
```
**注意**
如果 docker 发现文件内容被改变，则接下来的指令都不会再使用缓存。关于复制文件时需要处理的/，基本跟正常的 copy 一致

## ENV

格式:

```
ENV <key> <value>
ENV <key1>=<value1> <key2>=<value2>...
```

这个指令很简单，就是设置环境变量而已，无论是后面的其它指令，如 RUN，还是运行时的应用，都可以直接使用这里定义的环境变量。

```
ENV VERSION=1.0 DEBUG=on \
    NAME="Happy Feet"
```

这个例子中演示了如何换行，以及对含有空格的值用双引号括起来的办法，这和 Shell 下的行为是一致的。

## EXPOSE

为构建的镜像设置监听端口，使容器在运行时监听。格式：

```
EXPOSE <port> [<port>...]
```
EXPOSE 指令并不会让容器监听 host 的端口，如果需要，需要在 docker run 时使用 -p、-P 参数来发布容器端口到 host 的某个端口上。

## VOLUME

