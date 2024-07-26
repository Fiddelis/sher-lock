package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
	"github.com/gin-gonic/gin"
)

func GetAllLockers(c *gin.Context) {
	result, err := repository.GetAllLockers()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Armários não encontrados"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func GetLockerByID(c *gin.Context) {
	id := c.Param("id")
	lockerID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := repository.GetLockerByID(lockerID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Armário não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func CreateLocker(c *gin.Context) {
	var locker model.Locker
	if err := c.BindJSON(&locker); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar dados do armário"})
		return
	}

	result, err := repository.CreateLocker(locker)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar armário"})
		return
	}
	c.JSON(http.StatusOK, result)
}
