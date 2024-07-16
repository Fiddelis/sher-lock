package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/service"
	"github.com/gin-gonic/gin"
)

func GetAllClients(c *gin.Context) {
	result, err := service.GetAllClients()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Clientes não encontrados"})
		return
	}
	c.JSON(http.StatusOK, result)
}

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
