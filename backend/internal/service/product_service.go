package service

import (
	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
)

func GetAllProducts() ([]model.Product, error) {
	return repository.GetAllProducts()
}

func GetProductById(ID int) (model.Product, error) {
	return repository.GetProductByID(ID)
}

func GetProductsByLockerID(lockerID int) ([]model.Product, error) {
	return repository.GetProductsByLockerID(lockerID)
}

func CreateProduct(product model.Product) (string, error) {
	return repository.CreateProduct(product)
}
