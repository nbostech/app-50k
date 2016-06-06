package com.wavelabs.nfund.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ashkumar on 5/10/2016.
 */
public class TeamType {
    private long id;
    private String name;
    private String description;
    @SerializedName("tenant_id")
    private String tenantId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }


    public static HashMap<Long,String> getMapFromList(List<TeamType> teamTypeList){
        HashMap<Long, String> teamTMap = new HashMap<Long,String>();
        for (TeamType teamType:teamTypeList ) {
            teamTMap.put(teamType.getId(),teamType.getName());
        }
        return  teamTMap;
    }
}
