package com.example.demo3.demo3.services;

import com.example.demo3.demo3.models.Entity;
import com.example.demo3.demo3.models.PaymentTag;
import com.example.demo3.demo3.models.Tags;
import com.example.demo3.demo3.repositories.EntityRepoInterface;
import com.example.demo3.demo3.repositories.TagRepoInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingService implements TrackingInterface {

    private final EntityRepoInterface entityRepoInterface;
    private final TagRepoInterface tagRepoInterface;

    public TrackingService(EntityRepoInterface entityRepoInterface, TagRepoInterface tagRepoInterface) {
        this.entityRepoInterface = entityRepoInterface;
        this.tagRepoInterface = tagRepoInterface;
    }

    @Override
    public void startTracking(@NonNull final String entityId,@NonNull final List<Tags> tagsList) {
                Entity entity = new Entity(entityId,tagsList);
                entityRepoInterface.addNewEntity(entity);
                tagRepoInterface.addTagToRepo(entity);
    }

    @Override
    public int getCounts(@NonNull final List<Tags> tagsList) {
            if(!validateHierachy(tagsList))
                    return 0;
            return tagRepoInterface.getTagCount(tagsList);

    }

    @Override
    public void stopTracking(@NonNull final String entityId) {
        Entity entity = entityRepoInterface.getEntityById(entityId);
        entity.stopEntity();
        tagRepoInterface.removeTagCount(entity);
    }


    // should be converted to command design pattern and removing if else.
    public boolean validateHierachy(List<Tags> tagsList){

        if(!tagsList.get(0).getTag().getClass().toString().equals("class com.example.demo3.demo3.models.PaymentTag"))
                return false;

        if(tagsList.size() > 1 && !tagsList.get(1).getTag().getClass().toString().equals("class com.example.demo3.demo3.models.StateTag"))
            return false;

        if(tagsList.size() > 2 && !tagsList.get(2).getTag().getClass().toString().equals("class com.example.demo3.demo3.models.CityTag"))
            return false;

        return true;
    }

}
