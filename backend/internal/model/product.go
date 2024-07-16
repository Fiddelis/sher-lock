package model

type Product struct {
	ID             int     `json:"id"`
	ClientID       int     `json:"client_id"`
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
