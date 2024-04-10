package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.CargoType;
import com.ua.project.Autobase.services.CRUDInterface;

public interface CargoTypeService extends CRUDInterface<CargoType> {
    CargoType findCargoTypeById(long id);
}
