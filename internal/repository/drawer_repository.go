package repository

import (
	"github.com/fiddelis/sherlock/internal/database"
	"github.com/fiddelis/sherlock/internal/model"
)

func GetDrawerById(ID int) (model.Drawer, error) {
	var drawer model.Drawer
	result := database.DB.First(&drawer, ID)
	if result.Error != nil {
		return model.Drawer{}, result.Error
	}
	return drawer, nil
}

func GetDrawersByLockerId(lockerID int) ([]model.Drawer, error) {
	var drawer []model.Drawer
	result := database.DB.Where("locker_id", lockerID).Find(&drawer)
	if result.Error != nil {
		return nil, result.Error
	}
	return drawer, nil
}

func CreateDrawer(drawer model.Drawer) (model.Drawer, error) {
	result := database.DB.Create(&drawer)
	if result.Error != nil {
		return model.Drawer{}, nil
	}
	return drawer, nil
}

func UpdateDrawer(drawer model.Drawer) error {
	result := database.DB.Save(&drawer)
	if result.Error != nil {
		return result.Error
	}
	return nil
}
