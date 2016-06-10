package com.wavelabs.fundr.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashkumar on 5/6/2016.
 */
public class MediaFileDetail implements Serializable {
    @SerializedName("mediapath")
    private String mediaPath;
    @SerializedName("mediatype")
    private String mediaType;

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
