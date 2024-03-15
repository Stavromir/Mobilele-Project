```

docker run `
-e MYSQL_ALLOW_EMPTY_PASSWORD='yes' `
-e MYSQL_DATABASE='mobilele' `
-p 3308:3308 `
mysql:oracle --character-set-server=utf8mb4 --collation-server=utf8mb4_bin --default-authentication-plugin=mysql_native_password

```