package dto

type ClientDTO struct {
	Address     string       `json:"address"`
	Name        string       `json:"name"`
	PhoneNumber string       `json:"phone_number"`
	Mail        string       `json:"mail"`
	Products    []ProductDTO `json:"products"`
}

type ProductDTO struct {
	DrawerID       int     `json:"drawer_id"`
	DrawerLockerID int     `json:"drawer_locker_id"`
	Quantity       float64 `json:"quantity"`
	Weight         float64 `json:"weight"`
	Name           string  `json:"name"`
	Dimension      string  `json:"dimension"`
	EstimatedDate  string  `json:"estimated_date"`
	Address        string  `json:"address"`
	PassCode       string  `json:"pass_code"`
	Available      bool    `json:"available"`
}
