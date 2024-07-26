package model

type ClientProductData struct {
	Client   Client    `json:"client"`
	Products []Product `json:"products"`
}
