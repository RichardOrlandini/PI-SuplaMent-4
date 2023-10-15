# PI-SuplaMent-4
Pi 4 semestre 01


## Comandos Docker

### comando que para todos os containers: 

- docker stop $(docker ps -q)

### remove todos containers
- docker rm $(docker ps -a -q)

### comando que sobe todos containers 

- docker-compose up --build


#### Container Auth-DB

`docker run --name auth-db -p 5432:5432 -e POSTGRES_DB=auth-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 postgres:14.2`

#### Container Suplament-DB

`docker run --name suplament-db -p 5434:5432 -e POSTGRES_DB=suplament-db -e POSTGRES_USER=suplament -e POSTGRES_PASSWORD=suplament123 postgres:14.2`

#### Container Sales-DB

`docker run --name sales-db -p 27017:27017 -p 28017:28017 -e MONGODB_USER="admin" -e MONGODB_DATABASE="sales" -e MONGODB_PASS="123456" -v  c:/db tutum/mongodb`

#### Conexão no Mongoshell

`mongo "mongodb://admin:123456@localhost:27017/sales"`

#### Container RabbitMQ

`docker run --name sales_rabbit -p 5672:5672 -p 25676:25676 -p 15672:15672 rabbitmq:3-management`

### Execução docker-compose

`docker-compose up --build`

Para ignorar os logs, adicione a flag `-d`.
