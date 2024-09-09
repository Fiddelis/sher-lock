<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <style>
        body {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
            background-color: #f4f4f4;
        }

        .email-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            text-align: center;
        }
    </style>
    <title>Sherlock Email</title>
</head>
<body>
<table role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%" height="100%">
    <tr>
        <td align="center" valign="top">
            <div class="email-container">
                <h1>QRCode Gerado para retirada</h1>
                <h3>Informações do Produto</h3>
                <p><b>Nome:</b>${product.name}</p>
                <p><b>Quantidade:</b>${product.quantity}</p>
                <p><b>Data estimada:</b>${product.estimatedDate?date?string("dd/MM/yyyy")}</p>
                <p><b>Local para retirada:</b>${client.address}</p>

                <div class="qrcode">
                    <h3>QR-Code para Entrega</h3>
                    <img src="${delivery}">

                    <h3>QR-Code para Retirada</h3>
                    <img src="${withdrawn}">
                </div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>