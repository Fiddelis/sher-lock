package repository

import (
	"github.com/fiddelis/sherlock/internal/database"
	"github.com/fiddelis/sherlock/internal/model"
)

func GetAllLockers() ([]model.Locker, error) {
	var lockers []model.Locker
	result := database.DB.Find(&lockers)
	if result.Error != nil {
		return nil, result.Error
	}
	return lockers, nil
}

func GetLockerById(id int) (model.Locker, error) {
	var locker model.Locker
	result := database.DB.First(&locker, id)
	if result.Error != nil {
		return model.Locker{}, result.Error
	}
	return locker, nil
}

func CreateLocker(locker model.Locker) (model.Locker, error) {
	result := database.DB.Create(&locker)
	if result.Error != nil {
		return model.Locker{}, result.Error
	}
	return locker, nil
}
