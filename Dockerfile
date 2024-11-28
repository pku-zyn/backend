FROM openjdk:24-slim-bullseye

# 安装 Maven 3.9.9
RUN apt-get update && apt-get install -y curl && \
    curl -sL https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz | tar xz -C /opt && \
    ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn

# 设置工作目录
WORKDIR /app

# 复制你的项目文件到容器中
COPY . /app

# 构建应用
RUN mvn clean package -DskipTests

# 设置容器启动时运行的命令
CMD ["java", "-jar", "target/your-app.jar"]
