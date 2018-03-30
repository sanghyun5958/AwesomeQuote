package com.knily.awesomequote.helper;

public class Constants {

    public static final class DATABASE {
        public static final String DB_NAME = "topyaps";
        public static final String TABLE_POSTJSON = "postjson";
        public static final String TABLE_AUTHOR = "author";
        public static final String TABLE_BETTER_FEATURED_IMAGE = "betterFeatureImage";
        public static final String TABLE_CONTENT = "content";
        public static final String TABLE_EXCERPT = "excerpt";
        public static final String TABLE_GUEST_AUTHOR50 = "guestAuthor50";
        public static final String TABLE_LINKS = "links";
        public static final String TABLE_MEDIA_DETAILS = "mediaDetails";
        public static final String TABLE_MEDIUM_THUMB = "postjson";
        public static final String TABLE_REPLY = "reply";
        public static final String TABLE_SIZES = "sizes";
        public static final String TABLE_TITLE = "title";
        public static final int DB_VERSION = 1;
        //Constants declaration.........
        public static final String DB_ID="id";
        public static final String JSON_ID="ID";
        public static final String DATE="date";
        public static final String DATE_GMT="dateGmt";
        public static final String MODIFIED="modified";

        //Drop Tables if Exist....
        public static final String DROP_QUERY_POSTJSON = " DROP TABLE IF EXIST " + TABLE_POSTJSON;
        public static final String DROP_QUERY_AUTHOR = " DROP TABLE IF EXIST " + TABLE_AUTHOR;
        public static final String DROP_QUERY_BETTER_FEATURED_IMAGE = " DROP TABLE IF EXIST " + TABLE_BETTER_FEATURED_IMAGE;
        public static final String DROP_QUERY_CONTENT = " DROP TABLE IF EXIST " + TABLE_CONTENT;
        public static final String DROP_QUERY_EXCERPT = " DROP TABLE IF EXIST " + TABLE_EXCERPT;
        public static final String DROP_QUERY_GUEST_AUTHOR50 = " DROP TABLE IF EXIST " + TABLE_GUEST_AUTHOR50;
        public static final String DROP_QUERY_LINKS = " DROP TABLE IF EXIST " + TABLE_LINKS;
        public static final String DROP_QUERY_MEDIA_DETAILS = " DROP TABLE IF EXIST " + TABLE_MEDIA_DETAILS;
        public static final String DROP_QUERY_MEDIUM_THUMB = " DROP TABLE IF EXIST " + TABLE_MEDIUM_THUMB;
        public static final String DROP_QUERY_REPLY = " DROP TABLE IF EXIST " + TABLE_REPLY;
        public static final String DROP_QUERY_SIZES = " DROP TABLE IF EXIST " + TABLE_SIZES;
        public static final String DROP_QUERY_TITLE = " DROP TABLE IF EXIST " + TABLE_TITLE;

        //Table Creation....
        public static final String CREATE_TABLE_QUERY_POSTJSON = " CREATE TABLE " + TABLE_POSTJSON + "" +
                "("+DB_ID+" PRIMARY KEY AUTOINCREMENT," +
                "ID LONG not null," +
                "date TEXT not null," +
                "dateGmt TEXT not null," +
                "modified TEXT not null," +
                "slug TEXT not null," +
                "type TEXT not null," +
                "link TEXT not null," +
                "commentStatus TEXT not null," +
                "pingStatus TEXT not null," +
                "format TEXT not null," +
                "author INTEGER not null," +
                "featuredImage INTEGER not null,)";
        public static final String CREATE_TABLE_QUERY_AUTHOR = " CREATE TABLE " + TABLE_AUTHOR + "" +
                "(id PRIMARY KEY AUTOINCREMENT," +
                "embeddable BOOLEAN," +
                "href TEXT not null,)";
        public static final String CREATE_TABLE_QUERY_CONTENT = " CREATE TABLE " + TABLE_CONTENT + "" +
                "(id PRIMARY KEY, AUTOINCREMENT," +
                "rendered TEXT not null,)";
        public static final String CREATE_TABLE_QUERY_EXCERPT = " CREATE TABLE " + TABLE_EXCERPT + "" +
                "(id PRIMARY KEY AUTOINCREMENT," +
                "rendered TEXT not null,)";
        public static final String CREATE_TABLE_QUERY_GUEST_AUTHOR50 = " CREATE TABLE " + TABLE_GUEST_AUTHOR50 + "" +
                "(id PRIMARY KEY AUTOINCREMENT,+" +
                "file blob not null," +
                "width INTEGER not null," +
                "height INTEGER not null," +
                "mimeType TEXT not null," +
                "sourceUrl TEXT not null,)";
        public static final String CREATE_TABLE_QUERY_MEDIA_DETAILS = " CREATE TABLE " + TABLE_MEDIA_DETAILS + "" +
                "(id PRIMARY KEY AUTOINCREMENT," +
                "width INTEGER not null," +
                "height INTEGER not null," +
                "file blob not null,)";
        public static final String CREATE_TABLE_QUERY_REPLY = " CREATE TABLE " + TABLE_REPLY + "" +
                "(id PRIMARY KEY AUTOINCREMENT," +
                "(embeddable BOOLEAN," +
                "href TEXT not null,)";
        public static final String CREATE_TABLE_QUERY_TITLE = " CREATE TABLE " + TABLE_TITLE + "" +
                "(id PRIMARY KEY AUTOINCREMENT," +
                "rendered TEXT not null,)";
        public static final String CREATE_TABLE_QUERY_MEDIUM_THUMB = " CREATE TABLE " + TABLE_MEDIUM_THUMB + "" +
                "(id PRIMARY KEY AUTOINCREMENT," +
                "file blob not null," +
                "width INTEGER not null," +
                "height INTEGER not null," +
                "mimeType TEXT not null," +
                "sourceUrl TEXT not null,)";

        public static final String CREATE_TABLE_QUERY_BETTER_FEATURED_IMAGE = " CREATE TABLE " + TABLE_BETTER_FEATURED_IMAGE + "" +
                "(id PRIMARY KEY AUTOINCREMENT," +
                "(id INTEGER not null," +
                "altText TEXT not null," +
                "caption TEXT not null," +
                "description TEXT not null," +
                "mediaType TEXT not null," +
                "post INTEGER not null," +
                "sourceUrl TEXT not null,)";
    }
    public static final class REFERENCE{
        public static final String KNILY_DATA= Config.PACKAGE_NAME + "topyap";
    }
    public static final class Config{
        public static final String PACKAGE_NAME="com.dtechmonkey.d_techmonkey";
    }
}