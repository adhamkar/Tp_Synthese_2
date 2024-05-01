
package com.enset.tp_synthese.ui.bookconsulting.models;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageLink  implements Serializable {

    @SerializedName("smallThumbnail")
    private String smallThumbnail;

    @SerializedName("thumbnail")
    private String thumbnail;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

