package model

type Client struct {
	ID          int    `json:"id"`
	Address     string `json:"address"`
	Name        string `json:"name"`
	PhoneNumber string `json:"phone_number"`
	Mail        string `json:"mail"`
}
