#어디에서 시작할 것인지 (이미지)
FROM openjdk:17-ea-11-jdk-slim
# 컨테이너의 가상 디렉토리
VOLUME /tmp
# 현재 유저의 파일을 복사하겠다 (시작위치는 dockerfile 위치 기준)
COPY target/user-service-0.1.jar UserService.jar
# 이미지 실행 시 어떤 명령어로 실행할 것인지
ENTRYPOINT ["java","-jar","UserService.jar"]
