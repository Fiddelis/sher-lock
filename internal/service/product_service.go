package service

import (
	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
)

func GetAllProducts() ([]model.Product, error) {
	return repository.GetAllProducts()
}

func GetProductById(id int) (model.Product, error) {
	return repository.GetProductById(id)
}

func CreateProduct(product model.Product) (string, error) {
	return repository.CreateProduct(product)
}
