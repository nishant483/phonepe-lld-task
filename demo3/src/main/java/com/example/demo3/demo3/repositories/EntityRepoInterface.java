package com.example.demo3.demo3.repositories;

import com.example.demo3.demo3.models.Entity;

public interface EntityRepoInterface {

    public Entity getEntityById(String entityId);
    public void addNewEntity(Entity entity);
    public void stopEntityTracking(String entityId);
}
