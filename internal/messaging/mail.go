package messaging

import (
	"bytes"
	"io"
	"log"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"gopkg.in/gomail.v2"
)

func SendMailToClient(mail string, product model.Product, qrCode []byte) error {
	// Configurações do servidor SMTP da Hostinger
	smtpHost := "smtp.hostinger.com"
	smtpPort := 587
	smtpUser := ""
	smtpPass := ""

	reader := bytes.NewReader(qrCode)

	// Configurações do email
	to := mail
	subject := "Sherlock - Você ja pode retirar sua compra com seu código QR"
	body := `
    <!DOCTYPE html>
	<html>
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
	</head>
	<body>
    	<table role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%" height="100%">
        	<tr>
            	<td align="center" valign="top">
                	<div class="email-container">
                    	<h1>QRCode Gerado para retirada</h1>
                    	<h3>Informações do Produto</h3>
                    	<p><b>Nome:</b> ` + product.Name + `</p>
                    	<p><b>Quantidade:</b> ` + strconv.FormatFloat(product.Quantity, 'f', -1, 64) + `</p>
                    	<p><b>Data estimada:</b> ` + product.EstimatedDate + `</p>
                    	<p><b>Local para retirada:</b> ` + product.Address + `</p>
                    	<div class="qrcode">
                        	<h3>QR-Code para retirada</h3>
                        	<img src="cid:qrcode.png">
                    	</div>
                	</div>
            	</td>
        	</tr>
    	</table>
	</body>
	</html>
`

	m := gomail.NewMessage()
	m.SetHeader("From", smtpUser)
	m.SetHeader("To", to)
	m.SetHeader("Subject", subject)
	m.SetBody("text/html", body)
	m.Attach("qrcode.png", gomail.SetCopyFunc(func(w io.Writer) error {
		_, err := io.Copy(w, reader)
		return err
	}), gomail.Rename("qrcode.png"))

	d := gomail.NewDialer(smtpHost, smtpPort, smtpUser, smtpPass)

	if err := d.DialAndSend(m); err != nil {
		return err
	}

	log.Println("Email enviado com sucesso!")
	return nil
}
