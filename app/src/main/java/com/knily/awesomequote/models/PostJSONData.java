package com.knily.awesomequote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostJSONData implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer ID;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_gmt")
    @Expose
    private String dateGmt;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_gmt")
    @Expose
    private String modifiedGmt;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("excerpt")
    @Expose
    private Excerpt excerpt;
    @SerializedName("author")
    @Expose
    private Integer author;
    @SerializedName("featured_image")
    @Expose
    private Integer featuredImage;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("ping_status")
    @Expose
    private String pingStatus;
    @SerializedName("sticky")
    @Expose
    private Boolean sticky;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("better_featured_image")
    @Expose
    private BetterFeaturedImage betterFeaturedImage;
    @SerializedName("_links")
    @Expose
    private Links links;

    @SerializedName("featured_image_full_urls")
    @Expose
    private String FeaturedImgURL;



    public String getFeaturedImgURL() {
        return FeaturedImgURL;
    }

    public void setFeaturedImgURL(String featuredImgURL) {
        FeaturedImgURL = featuredImgURL;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return ID;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.ID = id;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The dateGmt
     */
    public String getDateGmt() {
        return dateGmt;
    }

    /**
     * @param dateGmt The date_gmt
     */
    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    /**
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * @param modified The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * @return The modifiedGmt
     */
    public String getModifiedGmt() {
        return modifiedGmt;
    }

    /**
     * @param modifiedGmt The modified_gmt
     */
    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    /**
     * @return The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * @return The content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * @return The excerpt
     */
    public Excerpt getExcerpt() {
        return excerpt;
    }

    /**
     * @param excerpt The excerpt
     */
    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * @return The author
     */
    public Integer getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Integer author) {
        this.author = author;
    }

    /**
     * @return The featuredImage
     */
    public Integer getFeaturedImage() {
        return featuredImage;
    }

    /**
     * @param featuredImage The featured_image
     */
    public void setFeaturedImage(Integer featuredImage) {
        this.featuredImage = featuredImage;
    }

    /**
     * @return The commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * @param commentStatus The comment_status
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * @return The pingStatus
     */
    public String getPingStatus() {
        return pingStatus;
    }

    /**
     * @param pingStatus The ping_status
     */
    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    /**
     * @return The sticky
     */
    public Boolean getSticky() {
        return sticky;
    }

    /**
     * @param sticky The sticky
     */
    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    /**
     * @return The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return The betterFeaturedImage
     */
    public BetterFeaturedImage getBetterFeaturedImage() {
        return betterFeaturedImage;
    }

    /**
     * @param betterFeaturedImage The better_featured_image
     */
    public void setBetterFeaturedImage(BetterFeaturedImage betterFeaturedImage) {
        this.betterFeaturedImage = betterFeaturedImage;
    }

    /**
     * @return The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * @param links The _links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Title: " + this.title.getRendered() + "\n"
                + "Content: " + this.content.getRendered() + "\n";
    }
}