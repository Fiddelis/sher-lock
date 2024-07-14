package repository

import (
	"github.com/fiddelis/sherlock/internal/database"
	"github.com/fiddelis/sherlock/internal/model"
	"github.com/google/uuid"
)

func GetAllProducts() ([]model.Product, error) {
	var products []model.Product
	result := database.DB.Find(&products)
	if result.Error != nil {
		return nil, result.Error
	}
	return products, nil
}

func GetProductByID(ID int) (model.Product, error) {
	var product model.Product
	result := database.DB.First(&product, ID)
	if result.Error != nil {
		return model.Product{}, nil
	}
	return product, nil
}

func CreateProduct(product model.Product) (string, error) {
	product.PassCode = uuid.NewString()
	result := database.DB.Create(&product)
	if result.Error != nil {
		return "", result.Error
	}
	return product.PassCode, nil
}

func UpdateProduct(product model.Product) error {
	result := database.DB.Save(&product)
	if result.Error != nil {
		return result.Error
	}
	return nil
}
