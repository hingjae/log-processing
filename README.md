### Docker Compose 실행
```shell
cd docker
docker compose up -d
```
### Kafka Topic 설정
```shell
docker exec -it <kafka-container> bash
kafka-topics --create --topic user-event-log --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### Kafka 이벤트 발행
```shell
kafka-console-producer --topic user-event-log --bootstrap-server localhost:9092
{"userId":"honey", "action":"CLICK", "resource":"/product/123", "clientIp":"127.0.0.1", "userAgent":"Chrome", "timestamp":"2025-07-22T10:30:00"}
```