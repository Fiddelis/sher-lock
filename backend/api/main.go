package main

import (
	"github.com/fiddelis/sherlock/internal/database"
	"github.com/fiddelis/sherlock/internal/router"
)

func main() {
	database.ConnectDB()
	router.HandleRequest()
}
