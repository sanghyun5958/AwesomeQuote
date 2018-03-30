package com.knily.awesomequote.models.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by P KUMAR on 26-10-2016.
 */

public class About implements Serializable {

        @SerializedName("href")
        @Expose
        private String href;

        /**
         *
         * @return
         * The href
         */
        public String getHref() {
            return href;
        }

        /**
         *
         * @param href
         * The href
         */
        public void setHref(String href) {
            this.href = href;
        }
}
