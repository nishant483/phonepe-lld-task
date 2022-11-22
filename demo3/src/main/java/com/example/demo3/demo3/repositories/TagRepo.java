package com.example.demo3.demo3.repositories;

import com.example.demo3.demo3.models.Entity;
import com.example.demo3.demo3.models.Tags;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TagRepo implements TagRepoInterface{

    public  final HashMap<Tags,ArrayList<String>> tagEntityMap; // contains tag to entity ids mapping

    public TagRepo() {
        this.tagEntityMap = new HashMap<>();
    }

    @Override
    public int getTagCount(List<Tags> tags) {

        if(tags.size() == 1){
                return tagEntityMap.get(tags.get(0)).size();
        }

        List<String> entityIds = new ArrayList<>(tagEntityMap.get(tags.get(0)));

        for (Tags tag:tags.subList(1,tags.size())
             ) {
                    if(!tagEntityMap.containsKey(tag))
                            return 0;
                    List<String> tagEntities = tagEntityMap.get(tag);
                    List<String> enteriesToRemove = new ArrayList<>();
                    for (String entityId:entityIds
                         ) {
                        if(!tagEntities.contains(entityId))
                            enteriesToRemove.add(entityId);
                    }
                    entityIds.removeAll(enteriesToRemove);
        }
        return entityIds.size();

        // upi - 3 entities id
        // bangalore -

    }

    @Override
    public void removeTagCount(Entity entity) {

        for(Tags tag:entity.getTagsList()){
            if(tagEntityMap.containsKey(tag))
                tagEntityMap.get(tag).remove(entity.getEntityId());
        }

    }

    @Override
    public void addTagToRepo(Entity entity) {

        for(Tags tag:entity.getTagsList()){
                if(tagEntityMap.containsKey(tag)) {
//                    ArrayList<String> data = new ArrayList<>(tagEntityMap.get(tag));
//                    data.add(entity.getEntityId());
//                    tagEntityMap.put(tag,data);
                    tagEntityMap.get(tag).add(entity.getEntityId());
                }
                else{
                    ArrayList<String> data = new ArrayList<>();
                    data.add(entity.getEntityId());
                    tagEntityMap.put(tag, data);
                }
        }
    }
}
