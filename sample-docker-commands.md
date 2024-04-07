```

./gradlew clean build -x test

docker push stavr1/mobilele:v1

docker build -t stavr1/mobilele:v2 -f deployment/Dockerfile .

docker-compose -f ________.yaml up

docker run `
-e MYSQL_ALLOW_EMPTY_PASSWORD='yes' `
-e MYSQL_DATABASE='mobilele' `
--hostname=db `
--network=softuni `
-p 3306:3306 `
mysql:oracle --character-set-server=utf8mb4 --collation-server=utf8mb4_bin --default-authentication-plugin=mysql_native_password


docker run `
-e MYSQL_HOST='db' `
-e MYSQL_USER='root' `
-e MYSQL_PASSWORD='' `
-p 8080:8080 `
--hostname=mobilele `
--network=softuni `
stavr1/mobilele:v3

```




