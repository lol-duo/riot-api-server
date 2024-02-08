#!/bin/bash

REPOSITORY=/home/ec2-user/riot-api-server
echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fla riot-api-server | awk '{print $1}')
echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
  echo "현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -9 $CURRENT_PID"
  kill -9 $CURRENT_PID
  sleep 10
fi
echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
echo "> JAR NAME: $JAR_NAME"
echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME
echo "> $JAR_NAME 실행"
nohup /opt/jdk-17/bin/java -jar -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9902 -Dcom.sun.management.jmxremote.rmi.port=9902 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=43.203.53.58 $JAR_NAME > $REPOSITORY/nohup.out  2>&1 &