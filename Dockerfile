#基础镜像：仓库是java，tag是8
FROM java:8

#将打包好的spring程序拷贝到容器中的指定位置
ADD target/sparrowadmin-0.0.1-SNAPSHOT.jar sparrowadmin_v1.jar

#容器对外暴露8080端口
#https://blog.csdn.net/qq_17639365/article/details/86655177
EXPOSE 8080

#运行 JAR 包的指令
ENTRYPOINT ["java","-jar","sparrowadmin_v1.jar"]


