package com.knily.awesomequote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by P KUMAR on 06-10-2016.
 */

public class MediaDetails implements Serializable {
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("sizes")
    @Expose
    private Sizes sizes;
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
     * The sizes
     */
    public Sizes getSizes() {
        return sizes;
    }

    /**
     *
     * @param sizes
     * The sizes
     */
    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }
}
