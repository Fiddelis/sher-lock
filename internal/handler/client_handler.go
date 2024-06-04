package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/service"
	"github.com/gin-gonic/gin"
)

// GetAllClients obtém todos os clientes da base de dados.
// @Summary Obter todos os clientes
// @Description Obter todos os clientes da base de dados
// @Tags clients
// @Produce json
// @Success 200 {array} model.Client
// @Router /api/client/all [get]
func GetAllClients(c *gin.Context) {
	result, err := service.GetAllClients()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Clientes não encontrados"})
		return
	}
	c.JSON(http.StatusOK, result)
}

// GetClientById obtém um cliente pelo seu ID.
// @Summary Obter cliente por ID
// @Description Obter um cliente da base de dados pelo seu ID
// @Tags clients
// @Produce json
// @Param id path int true "ID do Cliente"
// @Success 200 {object} model.Client
// @Router /api/client/{id} [get]
func GetClientById(c *gin.Context) {
	id := c.Param("id")
	clientID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := service.GetClientById(clientID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Cliente não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

// CreateClient cria um novo cliente.
// @Summary Criar cliente
// @Description Criar um novo cliente na base de dados
// @Tags clients
// @Accept json
// @Produce json
// @Param client body model.Client true "Dados do Cliente"
// @Success 200 {object} model.Client
// @Router /api/client [post]
func CreateClient(c *gin.Context) {
	var client model.Client
	if err := c.BindJSON(&client); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar dados do cliente"})
		return
	}

	result, err := service.CreateClient(client)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar cliente"})
		return
	}
	c.JSON(http.StatusOK, result)
}
