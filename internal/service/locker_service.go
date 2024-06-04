package service

import (
	"github.com/fiddelis/sherlock/internal/model"
	"github.com/fiddelis/sherlock/internal/repository"
)

func GetAllLockers() ([]model.Locker, error) {
	return repository.GetAllLockers()
}

func GetLockerById(id int) (model.Locker, error) {
	return repository.GetLockerById(id)
}

func CreateLocker(locker model.Locker) (model.Locker, error) {
	return repository.CreateLocker(locker)
}

// func UpdateLockerIP(ip string) (string) {

// }
