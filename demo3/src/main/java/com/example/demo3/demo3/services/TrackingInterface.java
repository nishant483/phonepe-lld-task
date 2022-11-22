package com.example.demo3.demo3.services;

import com.example.demo3.demo3.models.Tags;

import java.util.List;

public interface TrackingInterface {

    public void startTracking(String entityId,List<Tags> tagsList);
    public int getCounts(List<Tags> tagsList);
    public void stopTracking(String entityId);

}
