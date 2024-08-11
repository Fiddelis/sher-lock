### to-do
- [x] verificar se tem disponibilidade na gaveta especificada (criação do produto)
- [x] endpoint pra atualizar a disponibilidade da gaveta (produto retirado)
  - [] endpoint para remover o id da gaveta do produto
- [x] remover available do product
- [ ] passCode
- [ ] endpoint para atualizar disponibilidade da gaveta

### cadastro de produtos com cliente:
GET /api/product/register
```json
{
    "client": {
        "address": "Endereço da pessoa",
        "name": "luasc",
        "phone_number": "123123123",
        "mail": "lucas.ruan@ges.inatel.br"
    },
    "products": [
        {
        "drawer_id": 2,
        "locker_id": 2,
        "quantity": 1,
        "weight": 1,
        "name": "Nome do Produto de exemplo",
        "dimension": "15x10x10 cm",
        "estimated_date": "2024-06-20",
        "address": "Endereço do Locker para retirada"
    }]
}
```