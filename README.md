# Sher-Lock

## API

### Locker

/locker

| Requisição | Descrição | Tipo |
| -------------- | --------------- | ---- |
| /list | Retorna uma lista de lockers salvas | GET |

### Drawer

/drawer

| Requisição | Descrição | Tipo |
| --------------- | --------------- | --------------- |
| /list-in-locker/{locker_id} | Retorna uma lista de gavetas no locker | GET |
| /check/{id} | Retorna as informações da gaveta especificada | GET |
| /reserve/{id} | Reserva a gaveta especificada | POST |
| /create | Cria uma gaveta a partir da informação do Json | POST |

### Product

/product

| Requisição | Descrição | Tipo |
| --------------- | --------------- | --------------- |
| /register | Registra o produto com um respectivo cliente | POST |

### Client

/client

| Requisição | Descrição | Tipo |
| --------- | --------- | ---- |
| /check/{id} | Retorna o cliente nesse id especificado | GET |
| /create | Cria um cliente com o json enviado | POST |
| /update | Atualiza o cliente | POST | 


