package com.example.demo3.demo3.repositories;

import com.example.demo3.demo3.models.Entity;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class EntityRepo implements EntityRepoInterface{

    public final HashMap<String,Entity> entityHashMap;

    public EntityRepo() {
        this.entityHashMap = new HashMap<>();
    }

    @Override
    public Entity getEntityById(@NonNull final String entityId) {
        return entityHashMap.get(entityId);
    }

    @Override
    public void addNewEntity(@NonNull final Entity entity) {
            entityHashMap.put(entity.getEntityId(),entity);
    }

    @Override
    public void stopEntityTracking(@NonNull final String entityId) {
            entityHashMap.get(entityId).stopEntity();
    }
}
