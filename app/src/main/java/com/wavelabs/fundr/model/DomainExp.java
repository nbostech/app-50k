package com.wavelabs.fundr.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashkumar on 6/6/2016.
 */
public class DomainExp {;


    private Integer id;
    private String name;
    @SerializedName("parent_id")
    private Integer parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }


    public static List<DomainExp> getRelatedList(List<DomainExp> domainExpList, List<Integer> parentId){
        List<DomainExp> domainExps = new ArrayList<>();
        for (DomainExp domainExp:domainExpList
             ) {
            if(parentId!=null && parentId.contains(domainExp.getParent())){
                domainExps.add(domainExp);
            }else{
                domainExps.add(domainExp);
            }
        }
        return domainExps;
    }

    public static List<String> getRelatedListValues(List<DomainExp> domainExpList, List<Integer> parentId){
        Log.d("tst1",domainExpList.size()+ " ");

        List<String> domainExpsValues = new ArrayList<>();
        Log.d("tst2",domainExpsValues.size()+ " ");
        for (DomainExp domainExp:domainExpList) {
            if(parentId!=null && parentId.contains(domainExp.getParent())){
                domainExpsValues.add(domainExp.getName());
            }else if(domainExp.getParent() == null && parentId == null){
                domainExpsValues.add(domainExp.getName());
            }
        }
        Log.d("tst3",domainExpsValues.size()+ " ");
        return domainExpsValues;
    }

    public static List<Integer> getRelatedIds(List<DomainExp> domainExpList,List<String> values){

        List<Integer> domainExpsValues = new ArrayList<>();
        for (DomainExp domainExp:domainExpList) {

            if(values.contains(domainExp.getName())){

                domainExpsValues.add(domainExp.getId());
            }
        }
        return domainExpsValues;
    }
}
