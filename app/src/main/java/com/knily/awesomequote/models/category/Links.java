package com.knily.awesomequote.models.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by P KUMAR on 26-10-2016.
 */

public class Links implements Serializable {

        @SerializedName("self")
        @Expose
        private List<Self> self = new ArrayList<Self>();
        @SerializedName("collection")
        @Expose
        private List<Collection> collection = new ArrayList<Collection>();
        @SerializedName("about")
        @Expose
        private List<About> about = new ArrayList<About>();

        /**
         *
         * @return
         * The self
         */
        public List<Self> getSelf() {
            return self;
        }

        /**
         *
         * @param self
         * The self
         */
        public void setSelf(List<Self> self) {
            this.self = self;
        }

        /**
         *
         * @return
         * The collection
         */
        public List<Collection> getCollection() {
            return collection;
        }

        /**
         *
         * @param collection
         * The collection
         */
        public void setCollection(List<Collection> collection) {
            this.collection = collection;
        }

        /**
         *
         * @return
         * The about
         */
        public List<About> getAbout() {
            return about;
        }

        /**
         *
         * @param about
         * The about
         */
        public void setAbout(List<About> about) {
            this.about = about;
        }
}
