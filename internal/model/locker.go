package model

type Locker struct {
	ID        int     `json:"id"`
	Address   string  `json:"address"`
	Latitude  float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
}
