package main

import (
	_ "github.com/fiddelis/sherlock/docs"
	"github.com/fiddelis/sherlock/internal/database"
	"github.com/fiddelis/sherlock/internal/router"
)

// @title           Sherlock API
// @version         1.0
// @description     API de testes FETIN
// @host      localhost:8080
// @externalDocs.description  OpenAPI
func main() {
	database.ConnectDB()
	router.HandleRequest()
}
