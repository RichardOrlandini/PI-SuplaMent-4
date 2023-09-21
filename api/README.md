


### Passos para rodar o APP: 

1: Rode o banco postgres, ele deve ter o nome de supla-ment, as credênciais estão em aplication.properties
  (sujestão: rode o container do banco em docker: )
  - instale o docker
  - instale a imagem do postgres:14:2 : 
    `docker pull postgres:14.2`
  - Rode o container do banco: 
  ` docker run -itd -e POSTGRES_USER=suplament -e POSTGRES_PASSWORD=suplament123 -e POSTGRES_DB=suplament -p 5433:5432 -v /var/lib/postgresql/data:/data --name supla-ment postgres:14.2`
  - teste a conexão e rode o app.

### Login adm padrão
- http://localhost:8081/api/login
- POST
  - {
  "email": "adm@teste",
  "senha": "123456"
  } 