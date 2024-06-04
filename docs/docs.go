// Package docs Code generated by swaggo/swag. DO NOT EDIT
package docs

import "github.com/swaggo/swag"

const docTemplate = `{
    "schemes": {{ marshal .Schemes }},
    "swagger": "2.0",
    "info": {
        "description": "{{escape .Description}}",
        "title": "{{.Title}}",
        "contact": {},
        "version": "{{.Version}}"
    },
    "host": "{{.Host}}",
    "basePath": "{{.BasePath}}",
    "paths": {
        "/api/client": {
            "post": {
                "description": "Criar um novo cliente na base de dados",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "clients"
                ],
                "summary": "Criar cliente",
                "parameters": [
                    {
                        "description": "Dados do Cliente",
                        "name": "client",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/model.Client"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/model.Client"
                        }
                    }
                }
            }
        },
        "/api/client/all": {
            "get": {
                "description": "Obter todos os clientes da base de dados",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "clients"
                ],
                "summary": "Obter todos os clientes",
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/model.Client"
                            }
                        }
                    }
                }
            }
        },
        "/api/client/{id}": {
            "get": {
                "description": "Obter um cliente da base de dados pelo seu ID",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "clients"
                ],
                "summary": "Obter cliente por ID",
                "parameters": [
                    {
                        "type": "integer",
                        "description": "ID do Cliente",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/model.Client"
                        }
                    }
                }
            }
        },
        "/api/drawer": {
            "post": {
                "description": "Criar uma nova gaveta na base de dados",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "drawers"
                ],
                "summary": "Criar gaveta",
                "parameters": [
                    {
                        "description": "Dados da Gaveta",
                        "name": "drawer",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/model.Drawer"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/model.Drawer"
                        }
                    }
                }
            }
        },
        "/api/drawer/in_locker/{locker_id}": {
            "get": {
                "description": "Obter todas as gavetas de um armário da base de dados pelo ID do armário",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "drawers"
                ],
                "summary": "Obter gavetas por ID do Armário",
                "parameters": [
                    {
                        "type": "integer",
                        "description": "ID do Armário",
                        "name": "locker_id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/model.Drawer"
                            }
                        }
                    }
                }
            }
        },
        "/api/drawer/{id}": {
            "get": {
                "description": "Obter uma gaveta da base de dados pelo seu ID",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "drawers"
                ],
                "summary": "Obter gaveta por ID",
                "parameters": [
                    {
                        "type": "integer",
                        "description": "ID da Gaveta",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/model.Drawer"
                        }
                    }
                }
            }
        },
        "/api/locker": {
            "post": {
                "description": "Criar um novo armário na base de dados",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "lockers"
                ],
                "summary": "Criar armário",
                "parameters": [
                    {
                        "description": "Dados do Armário",
                        "name": "locker",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/model.Locker"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/model.Locker"
                        }
                    }
                }
            }
        },
        "/api/locker/all": {
            "get": {
                "description": "Obter todos os armários da base de dados",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "lockers"
                ],
                "summary": "Obter todos os armários",
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/model.Locker"
                            }
                        }
                    }
                }
            }
        },
        "/api/locker/{id}": {
            "get": {
                "description": "Obter um armário da base de dados pelo seu ID",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "lockers"
                ],
                "summary": "Obter armário por ID",
                "parameters": [
                    {
                        "type": "integer",
                        "description": "ID do Armário",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/model.Locker"
                        }
                    }
                }
            }
        },
        "/api/product": {
            "post": {
                "description": "Criar um novo cliente e produtos associados na base de dados",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "clients",
                    "products"
                ],
                "summary": "Criar cliente e produtos",
                "parameters": [
                    {
                        "description": "Dados do Cliente com Produtos",
                        "name": "clientDTO",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/dto.ClientDTO"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "type": "array",
                            "items": {
                                "type": "string"
                            }
                        }
                    }
                }
            }
        },
        "/api/product/all": {
            "get": {
                "description": "Obter todos os produtos da base de dados",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "products"
                ],
                "summary": "Obter todos os produtos",
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/model.Product"
                            }
                        }
                    }
                }
            }
        },
        "/api/product/{id}": {
            "get": {
                "description": "Obter um produto da base de dados pelo seu ID",
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "products"
                ],
                "summary": "Obter produto por ID",
                "parameters": [
                    {
                        "type": "integer",
                        "description": "ID do Produto",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/model.Product"
                        }
                    }
                }
            }
        }
    },
    "definitions": {
        "dto.ClientDTO": {
            "type": "object",
            "properties": {
                "address": {
                    "type": "string"
                },
                "mail": {
                    "type": "string"
                },
                "name": {
                    "type": "string"
                },
                "phone_number": {
                    "type": "string"
                },
                "products": {
                    "type": "array",
                    "items": {
                        "$ref": "#/definitions/dto.ProductDTO"
                    }
                }
            }
        },
        "dto.ProductDTO": {
            "type": "object",
            "properties": {
                "address": {
                    "type": "string"
                },
                "available": {
                    "type": "boolean"
                },
                "dimension": {
                    "type": "string"
                },
                "drawer_id": {
                    "type": "integer"
                },
                "drawer_locker_id": {
                    "type": "integer"
                },
                "estimated_date": {
                    "type": "string"
                },
                "name": {
                    "type": "string"
                },
                "pass_code": {
                    "type": "string"
                },
                "quantity": {
                    "type": "number"
                },
                "weight": {
                    "type": "number"
                }
            }
        },
        "model.Client": {
            "type": "object",
            "properties": {
                "address": {
                    "type": "string"
                },
                "id": {
                    "type": "integer"
                },
                "mail": {
                    "type": "string"
                },
                "name": {
                    "type": "string"
                },
                "phone_number": {
                    "type": "string"
                }
            }
        },
        "model.Drawer": {
            "type": "object",
            "properties": {
                "available": {
                    "type": "boolean"
                },
                "dimension": {
                    "type": "string"
                },
                "id": {
                    "type": "integer"
                },
                "locker_id": {
                    "type": "integer"
                }
            }
        },
        "model.Locker": {
            "type": "object",
            "properties": {
                "address": {
                    "type": "string"
                },
                "camera_ip": {
                    "type": "string"
                },
                "id": {
                    "type": "integer"
                },
                "latitude": {
                    "type": "number"
                },
                "locker_ip": {
                    "type": "string"
                },
                "longitude": {
                    "type": "number"
                }
            }
        },
        "model.Product": {
            "type": "object",
            "properties": {
                "address": {
                    "type": "string"
                },
                "available": {
                    "type": "boolean"
                },
                "client_id": {
                    "type": "integer"
                },
                "dimension": {
                    "type": "string"
                },
                "drawer_id": {
                    "type": "integer"
                },
                "drawer_locker_id": {
                    "type": "integer"
                },
                "estimated_date": {
                    "type": "string"
                },
                "id": {
                    "type": "integer"
                },
                "name": {
                    "type": "string"
                },
                "pass_code": {
                    "type": "string"
                },
                "quantity": {
                    "type": "number"
                },
                "weight": {
                    "type": "number"
                }
            }
        }
    },
    "externalDocs": {
        "description": "OpenAPI"
    }
}`

// SwaggerInfo holds exported Swagger Info so clients can modify it
var SwaggerInfo = &swag.Spec{
	Version:          "1.0",
	Host:             "localhost:8080",
	BasePath:         "",
	Schemes:          []string{},
	Title:            "Sherlock API",
	Description:      "API de testes FETIN",
	InfoInstanceName: "swagger",
	SwaggerTemplate:  docTemplate,
	LeftDelim:        "{{",
	RightDelim:       "}}",
}

func init() {
	swag.Register(SwaggerInfo.InstanceName(), SwaggerInfo)
}
