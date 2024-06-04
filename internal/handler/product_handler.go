package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/dto"
	"github.com/fiddelis/sherlock/internal/service"
	"github.com/gin-gonic/gin"
)

// GetAllProducts obtém todos os produtos da base de dados.
// @Summary Obter todos os produtos
// @Description Obter todos os produtos da base de dados
// @Tags products
// @Produce json
// @Success 200 {array} model.Product
// @Router /api/product/all [get]
func GetAllProducts(c *gin.Context) {
	result, err := service.GetAllProducts()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Produto não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

// GetProductById obtém um produto pelo seu ID.
// @Summary Obter produto por ID
// @Description Obter um produto da base de dados pelo seu ID
// @Tags products
// @Produce json
// @Param id path int true "ID do Produto"
// @Success 200 {object} model.Product
// @Router /api/product/{id} [get]
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

// CreateProductAndClient cria um novo cliente e produtos associados.
// @Summary Criar cliente e produtos
// @Description Criar um novo cliente e produtos associados na base de dados
// @Tags clients, products
// @Accept json
// @Produce json
// @Param clientDTO body dto.ClientDTO true "Dados do Cliente com Produtos"
// @Success 200 {array} string
// @Router /api/product [post]
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
