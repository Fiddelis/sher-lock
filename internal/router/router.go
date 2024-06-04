package router

import (
	"log"

	"github.com/fiddelis/sherlock/internal/handler"

	"github.com/gin-gonic/gin"
	swaggerFiles "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
)

func HandleRequest() {
	r := gin.Default()

	r.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))

	r.GET("/api/locker/all", handler.GetAllLockers)
	r.GET("/api/locker/:id", handler.GetLockerById)
	r.POST("/api/locker", handler.CreateLocker)

	r.GET("/api/client/all", handler.GetAllClients)
	r.GET("/api/client/:id", handler.GetClientById)
	r.POST("/api/client", handler.CreateClient)

	r.GET("/api/drawer/in_locker/:locker_id", handler.GetDrawersByLockerId)
	r.GET("/api/drawer/:id", handler.GetDrawerById)
	r.POST("/api/drawer", handler.CreateDrawer)

	r.GET("/api/product/all", handler.GetAllProducts)
	r.GET("/api/product/:id", handler.GetProductById)
	r.POST("/api/product", handler.CreateProductAndClient)

	log.Fatal(r.Run(":8080"))
}
