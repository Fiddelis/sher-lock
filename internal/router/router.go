package router

import (
	"log"

	"github.com/fiddelis/sherlock/internal/handler"

	"github.com/gin-gonic/gin"
)

func HandleRequest() {
	r := gin.Default()

	r.GET("/api/locker/all", handler.GetAllLockers)
	r.GET("/api/locker/:id", handler.GetLockerByID)
	r.POST("/api/locker", handler.CreateLocker)

	r.GET("/api/client/all", handler.GetAllClients)
	r.GET("/api/client/:id", handler.GetClientByID)
	r.POST("/api/client", handler.CreateClient)

	r.GET("/api/drawer/by_locker/:locker_id", handler.GetDrawersByLockerID)
	r.GET("/api/drawer/:id", handler.GetDrawerByID)
	r.POST("/api/drawer", handler.CreateDrawer)
	r.PUT("/api/drawer/update", handler.UpdateDrawer)

	r.GET("/api/product/all", handler.GetAllProducts)
	r.GET("/api/product/:id", handler.GetProductByID)
	r.GET("/api/product/by_locker/:lockerID", handler.GetProductsByLockerID)
	r.POST("/api/product/register", handler.CreateProductAndClient)

	log.Fatal(r.Run(":8080"))
}
