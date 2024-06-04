package service

import (
	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
)

func GetDrawerById(id int) (model.Drawer, error) {
	return repository.GetDrawerById(id)
}

func GetDrawersByLockerId(lockerID int) ([]model.Drawer, error) {
	return repository.GetDrawersByLockerId(lockerID)
}

func CreateDrawer(drawer model.Drawer) (model.Drawer, error) {
	return repository.CreateDrawer(drawer)
}
