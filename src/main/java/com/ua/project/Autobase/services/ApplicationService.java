package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.Application;
import com.ua.project.Autobase.services.CRUDInterface;

import java.util.List;

public interface ApplicationService extends CRUDInterface<Application> {
    List<Application> getFreeApplications();
}
