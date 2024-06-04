package service

import (
	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
)

func GetAllClients() ([]model.Client, error) {
	return repository.GetAllClients()
}

func GetClientById(id int) (model.Client, error) {
	return repository.GetClientById(id)
}

func CreateClient(client model.Client) (int, error) {
	return repository.CreateClient(client)
}
