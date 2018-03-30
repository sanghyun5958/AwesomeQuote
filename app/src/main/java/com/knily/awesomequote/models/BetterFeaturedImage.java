package com.knily.awesomequote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by P KUMAR on 06-10-2016.
 */

public class BetterFeaturedImage implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("alt_text")
    @Expose
    private String altText;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("media_details")
    @Expose
    private MediaDetails mediaDetails;
    @SerializedName("post")
    @Expose
    private Integer post;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The altText
     */
    public String getAltText() {
        return altText;
    }

    /**
     *
     * @param altText
     * The alt_text
     */
    public void setAltText(String altText) {
        this.altText = altText;
    }

    /**
     *
     * @return
     * The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     *
     * @param caption
     * The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     *
     * @param mediaType
     * The media_type
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     *
     * @return
     * The mediaDetails
     */
    public MediaDetails getMediaDetails() {
        return mediaDetails;
    }

    /**
     *
     * @param mediaDetails
     * The media_details
     */
    public void setMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
    }

    /**
     *
     * @return
     * The post
     */
    public Integer getPost() {
        return post;
    }

    /**
     *
     * @param post
     * The post
     */
    public void setPost(Integer post) {
        this.post = post;
    }

    /**
     *
     * @return
     * The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     *
     * @param sourceUrl
     * The source_url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
