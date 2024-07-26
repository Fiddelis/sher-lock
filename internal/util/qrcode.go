package util

import (
	"log"

	qrcode "github.com/skip2/go-qrcode"
)

func GenerateQRCode(data string) []byte {
	byte, err := qrcode.Encode(data, qrcode.Medium, 256)
	if err != nil {
		log.Fatalf("Erro ao Gerar QRCode\n")
	}

	return byte
}
