
package com.enset.tp_synthese.ui.bookconsulting.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class volumeInfo implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("authors")
    private List<String> authors;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("description")
    private String description;
    @SerializedName("imageLinks")
    private ImageLink imagelink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ImageLink getImagelink() {
        return imagelink;
    }

    public void setImagelink(ImageLink imagelink) {
        this.imagelink = imagelink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

