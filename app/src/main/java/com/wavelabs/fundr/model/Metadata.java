package com.wavelabs.fundr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ashkumar on 5/5/2016.
 */
public class Metadata implements Serializable{

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static HashMap<Long,String> getMapFromList(List<Metadata> metadataList){
        HashMap<Long, String> metadataMap = new HashMap<>();
        for (Metadata metadata:metadataList ) {
            metadataMap.put(metadata.getId(),metadata.getName());
        }
        return  metadataMap;
    }
    public static List<String> getListValues(List<Metadata> metadataList){
        List<String> metadataList1 = new ArrayList<>();
        for (Metadata metadata:metadataList ) {
            metadataList1.add(metadata.getName());
        }
        return  metadataList1;
    }
}
