package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/service"
	"github.com/gin-gonic/gin"
)

// GetDrawerById obtém uma gaveta pelo seu ID.
// @Summary Obter gaveta por ID
// @Description Obter uma gaveta da base de dados pelo seu ID
// @Tags drawers
// @Produce json
// @Param id path int true "ID da Gaveta"
// @Success 200 {object} model.Drawer
// @Router /api/drawer/{id} [get]
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

// GetDrawersByLockerId obtém todas as gavetas de um armário pelo seu ID.
// @Summary Obter gavetas por ID do Armário
// @Description Obter todas as gavetas de um armário da base de dados pelo ID do armário
// @Tags drawers
// @Produce json
// @Param locker_id path int true "ID do Armário"
// @Success 200 {array} model.Drawer
// @Router /api/drawer/in_locker/{locker_id} [get]
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

// CreateDrawer cria uma nova gaveta.
// @Summary Criar gaveta
// @Description Criar uma nova gaveta na base de dados
// @Tags drawers
// @Accept json
// @Produce json
// @Param drawer body model.Drawer true "Dados da Gaveta"
// @Success 200 {object} model.Drawer
// @Router /api/drawer [post]
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
