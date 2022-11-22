package com.example.demo3.demo3.repositories;

import com.example.demo3.demo3.models.Entity;
import com.example.demo3.demo3.models.Tags;

import java.util.List;

public interface TagRepoInterface {

    public int getTagCount(List<Tags> tags);
    public void addTagToRepo(Entity entity);

    public void removeTagCount(Entity entity);

}
