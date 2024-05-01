
package com.enset.tp_synthese.ui.bookconsulting.models;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Book implements Serializable {
    @SerializedName("volumeInfo")
    private volumeInfo volumeinfo;

    public volumeInfo getVolumeinfo() {
        return volumeinfo;
    }

    public void setVolumeinfo(volumeInfo volumeinfo) {
        this.volumeinfo = volumeinfo;
    }
}

