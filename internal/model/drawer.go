package model

type Drawer struct {
	ID        int    `json:"id"`
	LockerID  int    `json:"locker_id"`
	Dimension string `json:"dimension"`
	Available bool   `json:"available"`
}
