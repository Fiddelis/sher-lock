package dto

import "github.com/fiddelis/sherlock/internal/model"

func (c *ClientDTO) ToModel() model.Client {
	return model.Client{
		Address:     c.Address,
		Name:        c.Name,
		PhoneNumber: c.PhoneNumber,
		Mail:        c.Mail,
	}
}

func (p *ProductDTO) ToModel(clientID int) model.Product {
	return model.Product{
		ClientID:       clientID,
		DrawerID:       p.DrawerID,
		DrawerLockerID: p.DrawerLockerID,
		Quantity:       p.Quantity,
		Weight:         p.Weight,
		Name:           p.Name,
		Dimension:      p.Dimension,
		EstimatedDate:  p.EstimatedDate,
		Address:        p.Address,
		PassCode:       p.PassCode,
		Available:      p.Available,
	}
}
