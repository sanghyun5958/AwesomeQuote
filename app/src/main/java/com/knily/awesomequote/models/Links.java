package com.knily.awesomequote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by P KUMAR on 06-10-2016.
 */

public class Links implements Serializable {
    @SerializedName("author")
    @Expose
    private List<Author> author = new ArrayList<Author>();
    @SerializedName("replies")
    @Expose
    private List<Reply> replies = new ArrayList<Reply>();
    /**
     *
     * @return
     * The author
     */
    public List<Author> getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The replies
     */
    public List<Reply> getReplies() {
        return replies;
    }

    /**
     *
     * @param replies
     * The replies
     */
    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
