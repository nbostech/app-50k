package com.app50knetwork.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ashkumar on 5/6/2016.
 */
public class MediaFile implements Serializable {

    @SerializedName("id")
    private Long id;
    private String extension;
    @SerializedName("mediaFileDetailsList")
    private List<MediaFileDetail> mediaFileDetailList = new ArrayList<MediaFileDetail>();
    @SerializedName("supportedsizes")
    private String supportedSizes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }



    public void setMediaFileDetailList(List<MediaFileDetail> mediaFileDetailList) {
        this.mediaFileDetailList = mediaFileDetailList;
    }


    public List<MediaFileDetail> getMediaFileDetailList() {
        return mediaFileDetailList;
    }




    public String getSupportedSizes() {
        return supportedSizes;
    }

    public void setSupportedSizes(String supportedSizes) {
        this.supportedSizes = supportedSizes;
    }


    public String getMediaFileURLStr(String mediaType){
        String mediaPath ="";

        for (MediaFileDetail mediaFileDetail: getMediaFileDetailList()) {
            if(mediaFileDetail.getMediaType().equalsIgnoreCase(mediaType))
                return mediaFileDetail.getMediaPath();
        }

        return mediaPath;
    }


    public Map<String,String> getMediaFileDim(){
        Map<String,String> mediaFileDimMap = new HashMap<String,String>();
        String supSizeStr = getSupportedSizes();
        String[] supSizeArr = supSizeStr.split(",");
        for (String dimStr:supSizeArr ) {
            String[] dimStrArr = dimStr.split(":");
            mediaFileDimMap.put(dimStrArr[0],dimStrArr[1]);
        }
        return mediaFileDimMap;
    }
}
