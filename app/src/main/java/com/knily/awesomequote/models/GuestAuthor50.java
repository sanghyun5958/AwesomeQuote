package com.knily.awesomequote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by P KUMAR on 06-10-2016.
 */

public class GuestAuthor50 implements Serializable {
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("mime-type")
    @Expose
    private String mimeType;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;

    /**
     *
     * @return
     * The file
     */
    public String getFile() {
        return file;
    }

    /**
     *
     * @param file
     * The file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     *
     * @return
     * The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     *
     * @param mimeType
     * The mime-type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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
