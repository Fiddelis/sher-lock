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
	smtpHost := ""
	smtpPort := 1
	smtpUser := ""
	smtpPass := ""

	reader := bytes.NewReader(qrCode)

	// Configurações do email
	to := mail
	subject := "Sherlock - QRCode"
	body := `
    <html>
		<body>
			<h1>QRCode Gerado para retirada</h1>
			
			<h3>Informações do Produto</h3>
			<p><b>Nome:</b> ` + product.Name + `</p>
			<p><b>Quantidade:</b> ` + strconv.FormatFloat(product.Quantity, 'f', -1, 64) + `</p>
			<p><b>Data estimada:</b> ` + product.EstimatedDate + `</p>
			<p><b>Local do locker para retirada:</b> ` + product.Address + `</p>
			<h3>QR-Code para retirada</h3>
			<img src="cid:qrcode.png">
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
