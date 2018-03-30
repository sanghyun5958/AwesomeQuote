package com.knily.awesomequote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by P KUMAR on 06-10-2016.
 */

public class Sizes implements Serializable {

    @SerializedName("large")
    @Expose
    private MediumThumb mediumThumb;

    @SerializedName("guest-author-50")
    @Expose
    private GuestAuthor50 guestAuthor50;
    /**
     *
     * @return
     * The mediumThumb
     */
    public MediumThumb getMediumThumb() {
        return mediumThumb;
    }

    /**
     *
     * @param mediumThumb
     * The medium-thumb
     */
    public void setMediumThumb(MediumThumb mediumThumb) {
        this.mediumThumb = mediumThumb;
    }

    /**
     *
     * @return
     * The guestAuthor50
     */
    public GuestAuthor50 getGuestAuthor50() {
        return guestAuthor50;
    }

    /**
     *
     * @param guestAuthor50
     * The guest-author-50
     */
    public void setGuestAuthor50(GuestAuthor50 guestAuthor50) {
        this.guestAuthor50 = guestAuthor50;
    }
}
