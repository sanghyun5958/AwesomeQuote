package com.knily.awesomequote.models.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by P KUMAR on 26-10-2016.
 */

public class Category implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer ID;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("taxonomy")
        @Expose
        private String taxonomy;
        @SerializedName("parent")
        @Expose
        private Integer parent;
        @SerializedName("_links")
        @Expose
        private Links links;

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return ID;
        }

        /**
         *
         * @param ID
         * The id
         */
        public void setId(Integer ID) {
            this.ID=ID;
        }

        /**
         *
         * @return
         * The count
         */
        public Integer getCount() {
            return count;
        }

        /**
         *
         * @param count
         * The count
         */
        public void setCount(Integer count) {
            this.count = count;
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
         * The link
         */
        public String getLink() {
            return link;
        }

        /**
         *
         * @param link
         * The link
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The slug
         */
        public String getSlug() {
            return slug;
        }

        /**
         *
         * @param slug
         * The slug
         */
        public void setSlug(String slug) {
            this.slug = slug;
        }

        /**
         *
         * @return
         * The taxonomy
         */
        public String getTaxonomy() {
            return taxonomy;
        }

        /**
         *
         * @param taxonomy
         * The taxonomy
         */
        public void setTaxonomy(String taxonomy) {
            this.taxonomy = taxonomy;
        }

        /**
         *
         * @return
         * The parent
         */
        public Integer getParent() {
            return parent;
        }

        /**
         *
         * @param parent
         * The parent
         */
        public void setParent(Integer parent) {
            this.parent = parent;
        }

        /**
         *
         * @return
         * The links
         */
        public Links getLinks() {
            return links;
        }

        /**
         *
         * @param links
         * The _links
         */
        public void setLinks(Links links) {
            this.links = links;
        }
}
