package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/service"
	"github.com/gin-gonic/gin"
)

// GetAllLockers obtém todos os armários da base de dados.
// @Summary Obter todos os armários
// @Description Obter todos os armários da base de dados
// @Tags lockers
// @Produce json
// @Success 200 {array} model.Locker
// @Router /api/locker/all [get]
func GetAllLockers(c *gin.Context) {
	result, err := service.GetAllLockers()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Armários não encontrados"})
		return
	}
	c.JSON(http.StatusOK, result)
}

// GetLockerById obtém um armário pelo seu ID.
// @Summary Obter armário por ID
// @Description Obter um armário da base de dados pelo seu ID
// @Tags lockers
// @Produce json
// @Param id path int true "ID do Armário"
// @Success 200 {object} model.Locker
// @Router /api/locker/{id} [get]
func GetLockerById(c *gin.Context) {
	id := c.Param("id")
	lockerID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := service.GetLockerById(lockerID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Armário não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

// CreateLocker cria um novo armário.
// @Summary Criar armário
// @Description Criar um novo armário na base de dados
// @Tags lockers
// @Accept json
// @Produce json
// @Param locker body model.Locker true "Dados do Armário"
// @Success 200 {object} model.Locker
// @Router /api/locker [post]
func CreateLocker(c *gin.Context) {
	var locker model.Locker
	if err := c.BindJSON(&locker); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar dados do armário"})
		return
	}

	result, err := service.CreateLocker(locker)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar armário"})
		return
	}
	c.JSON(http.StatusOK, result)
}
