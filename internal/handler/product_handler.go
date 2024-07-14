package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/dto"
	"github.com/fiddelis/sherlock/internal/service"
	"github.com/gin-gonic/gin"
)

func GetAllProducts(c *gin.Context) {
	result, err := service.GetAllProducts()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Produto não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func GetProductById(c *gin.Context) {
	id := c.Param("id")
	productID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := service.GetProductById(productID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Produto não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func CreateProductAndClient(c *gin.Context) {
	var passCodes []string
	var clientDTO dto.ClientDTO
	if err := c.BindJSON(&clientDTO); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar dados do cliente com produtos"})
		return
	}

	client := clientDTO.ToModel()

	clientID, err := service.CreateClient(client)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar cliente com produtos"})
		return
	}

	for _, productDTO := range clientDTO.Products {
		product := productDTO.ToModel(clientID)
		passCode, err := service.CreateProduct(product)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar produtos"})
			return
		}
		passCodes = append(passCodes, passCode)
	}
	c.JSON(http.StatusOK, passCodes)
}
