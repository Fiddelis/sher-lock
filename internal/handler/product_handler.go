package handler

import (
	"net/http"
	"strconv"

	"github.com/fiddelis/sherlock/internal/messaging"
	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
	"github.com/fiddelis/sherlock/internal/util"
	"github.com/gin-gonic/gin"
)

func GetAllProducts(c *gin.Context) {
	result, err := repository.GetAllProducts()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Produto não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func GetProductByID(c *gin.Context) {
	id := c.Param("id")
	productID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := repository.GetProductByID(productID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Produto não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func GetProductsByLockerID(c *gin.Context) {
	id := c.Param("lockerID")
	lockerID, err := strconv.Atoi(id)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "ID incorreto"})
		return
	}

	result, err := repository.GetProductsByLockerID(lockerID)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Locker não encontrado"})
		return
	}
	c.JSON(http.StatusOK, result)
}

func CreateProductAndClient(c *gin.Context) {
	var passCodes []string
	var r model.ClientProductData

	if err := c.BindJSON(&r); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao decodificar dados do cliente/produto"})
		return
	}

	clientID, err := repository.CreateClient(r.Client)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar cliente"})
		return
	}

	for _, product := range r.Products {
		product.ClientID = clientID

		// Pegar endereço do locker para retirada
		locker, err := repository.GetLockerByID(product.LockerID)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao encontrar locker"})
			return
		}
		product.Address = locker.Address

		// Verificar disponibilidade da gaveta
		drawer, err := repository.GetDrawerByID(product.DrawerID)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar produto"})
			return
		}

		if !drawer.Available {
			c.JSON(http.StatusBadRequest, gin.H{"error": "Gaveta não está disponivel"})
			return
		}

		passCode, err := repository.CreateProduct(product)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao criar produto"})
			return
		}

		drawer.Available = false
		err = repository.UpdateDrawer(drawer)
		if err != nil {
			return
		}

		passCodes = append(passCodes, passCode)

		qrCode := util.GenerateQRCode(passCode)
		err = messaging.SendMailToClient(r.Client.Mail, product, qrCode)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": "Erro ao enviar o email"})
			return
		}
	}
	c.JSON(http.StatusOK, passCodes)
}
