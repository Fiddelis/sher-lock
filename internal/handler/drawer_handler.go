package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
	"github.com/gin-gonic/gin"
)

func GetDrawerByID(c *gin.Context) {
	id := c.Param("id")
	drawerID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := repository.GetDrawerByID(drawerID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Gaveta não encontrada"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func GetDrawersByLockerID(c *gin.Context) {
	lockerID := c.Param("locker_id")
	id, err := strconv.Atoi(lockerID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID do Armário incorreto"})
		return
	}

	result, err := repository.GetDrawersByLockerID(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Gavetas não encontradas"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func CreateDrawer(c *gin.Context) {
	var drawer model.Drawer
	if err := c.BindJSON(&drawer); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar o JSON"})
		return
	}

	result, err := repository.CreateDrawer(drawer)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar gaveta"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func UpdateDrawer(c *gin.Context) {
	var drawer model.Drawer

	if err := c.BindJSON(&drawer); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar o JSON"})
		return
	}

	if err := repository.UpdateDrawer(drawer); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Gaveta não encontrada"})
		return
	}

	c.JSON(http.StatusOK, "Gaveta atualizada")
}
