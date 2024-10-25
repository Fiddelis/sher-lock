@echo off

:: Caminho do primeiro JAR
set JAR_1=".\api-service\target\api-service-0.0.1-SNAPSHOT.jar"
:: Caminho do segundo JAR
set JAR_2=".\mail-service\target\mail-service-0.0.1-SNAPSHOT.jar"

:: Abrir o primeiro Spring Boot em uma nova janela de cmd
start cmd /k java -jar %JAR_1%

:: Abrir o segundo Spring Boot em outra nova janela de cmd
start cmd /k java -jar %JAR_2%