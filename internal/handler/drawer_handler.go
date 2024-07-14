package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/service"
	"github.com/gin-gonic/gin"
)

func GetDrawerById(c *gin.Context) {
	id := c.Param("id")
	drawerID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := service.GetDrawerById(drawerID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Gaveta não encontrada"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func GetDrawersByLockerId(c *gin.Context) {
	lockerID := c.Param("locker_id")
	id, err := strconv.Atoi(lockerID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID do Armário incorreto"})
		return
	}

	result, err := service.GetDrawersByLockerId(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Gavetas não encontradas"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func CreateDrawer(c *gin.Context) {
	var drawer model.Drawer
	if err := c.BindJSON(&drawer); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar dados da gaveta"})
		return
	}

	result, err := service.CreateDrawer(drawer)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar gaveta"})
		return
	}
	c.JSON(http.StatusOK, result)
}
