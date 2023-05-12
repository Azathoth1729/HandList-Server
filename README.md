# HandList-Server

This is the server side(backend) of [HandList](https://github.com/Azathoth1729/HandList)

## Usage
下面展示如何在本地运行并构建该项目。

### Requirements

Jetbrains IDEA

### Step 1

clone本项目，从IDEA导入该项目

### Step 2
项目用到的数据库是部署在容器内的Postgresql数据库.我们还需使用额外的步骤来创建该数据库并与Springboot进行连接.

在项目的根目录下我们定义了一个`docker-compose.yml`文件，使用
`docker-compose up`命令可以自动完成构建镜像，创建并启动 Postgresql 数据库服务。

``` bash
docker-compose up
```

显示当前运行的容器
```bash
docker ps
```

使用 `docker exec` 在指定容器内运行进入数据库的命令
```bash
# docker exec -it [container name] psql -U [username] 
docker exec -it handlist_postgres psql -U marisa
```

在 `src/main/resources/application.yml` 文件中，我们定义了springboot用于连接数据库的url:
```
jdbc:postgresql://localhost:5332/handlistdb
```

我们尚未创建该数据库，因此需要创建一个名为 handlistdb 的数据库：
```bash
CREATE DATABASE handlistdb;
```

### Step 3

使用IDEA运行