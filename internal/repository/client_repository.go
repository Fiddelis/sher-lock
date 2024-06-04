package repository

import (
	"github.com/fiddelis/sherlock/internal/database"
	"github.com/fiddelis/sherlock/internal/model"
)

func GetAllClients() ([]model.Client, error) {
	var clients []model.Client
	result := database.DB.Find(&clients)
	if result.Error != nil {
		return nil, result.Error
	}
	return clients, nil
}

func GetClientById(id int) (model.Client, error) {
	var client model.Client

	result := database.DB.First(&client, id)
	if result.Error != nil {
		return client, result.Error
	}
	return client, nil
}

func CreateClient(client model.Client) (int, error) {
	result := database.DB.Create(&client)
	if result.Error != nil {
		return -1, result.Error
	}
	return client.ID, nil
}
