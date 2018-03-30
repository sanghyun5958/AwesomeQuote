package com.knily.awesomequote;

import com.knily.awesomequote.models.AuthorInfo;
import com.knily.awesomequote.models.PostJSONData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostRetrieve {
    int OFFSET = 1;
    int PER_PAGE_INITIAL = 21;
    int PER_PAGE_AFTER = 20;
    int ENGLISH_LANGUAGE=35459;
    int HINDI_LANGUAGE=2579;
    int BENGALI_LANGUAGE=31188;
    int TAMIL_LANGUAGE=17313;
    String ENGLISH=HINDI_LANGUAGE+","+BENGALI_LANGUAGE+","+TAMIL_LANGUAGE;

    /**
     * http://topyaps.com/wp-json/wp/v2/posts?page=3&per_page=20
     */


    //All pages with all languages
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostJSONData>> getPostList(@Query("page") int page, @Query("per_page") int perPageCount);


    //All pages with all languages
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostJSONData>> getPostList2(@Query("page") int page, @Query("per_page") int perPageCount);

    //All pages with english language
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostJSONData>> getPostListEng(@Query("tags_exclude") String english_lang, @Query("page") int page, @Query("per_page") int perPageCount);

    //All pages with hindi or bengali languages
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostJSONData>> getPostListMix(@Query("tags") String language, @Query("page") int page, @Query("per_page") int perPageCount);

    /*@GET("wp-json/wp/v2/posts/?filter[posts_per_page]=31")
    Call<List<PostJSONData>> getPostList();*/

    /*@GET("wp-json/wp/v2/posts/?filter[posts_per_page]=21")
    Call<List<PostJSONData>> getPostList();*/
    /*@GET("wp-json/wp/v2/posts/?filter[category_name]=news")
    Call<List<PostJSONData>> getListNews();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=art-and-culture")
    Call<List<PostJSONData>> getListCulture();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=fun-and-entertainment")
    Call<List<PostJSONData>> getListEntertainment();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=military")
    Call<List<PostJSONData>> getListMilitary();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=women")
    Call<List<PostJSONData>> getListWomen();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=nature")
    Call<List<PostJSONData>> getListNature();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=food")
    Call<List<PostJSONData>> getListFood();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=history")
    Call<List<PostJSONData>> getListHistory();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=videos")
    Call<List<PostJSONData>> getListVideos();

    @GET("wp-json/wp/v2/posts/?filter[category_name]=opinion")
    Call<List<PostJSONData>> getListOpinion();
*/

    //For Language selection
    /*http://topyaps.com/wp-json/wp/v2/posts?filter[category_name]=english_lang&page=2*/
    /*http://topyaps.com/wp-json/wp/v2/posts?filter[tag]=bangla&page=3*/
    /*http://topyaps.com/wp-json/wp/v2/posts?filter[tag]=hindi&page=3*/

    /**
     * http://topyaps.com/wp-json/wp/v2/posts?categories=35459,3&page=5
     * @param
     * @return
     */
    //all pages with language
    //@GET("wp-json/wp/v2/posts/")
    //Call<List<PostJSONData>> getPostList(@Query("page") int page, @Query("per_page") int perPageCount);

    //Pages with category with all languages
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostJSONData>> getPostListCategory(@Query("categories") String categoryID, @Query("page") int page, @Query("per_page") int perPageCount);

    //pages with category and language (http://topyaps.com/wp-json/wp/v2/posts/?filter[category_name]=english_lang&filter[category_name]=videos&page=2)

    //Pages with category with english language
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostJSONData>> getPostListCategoryWithLanguageEng(@Query("tags_exclude") String english_lang, @Query("categories") String categoryID, @Query("page") int page, @Query("per_page") int perPageCount);

    //All page with category with hindi or bengali language
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostJSONData>> getPostListCategoryWithLanguageMix(@Query("tags") String language, @Query("categories") String categoryID, @Query("page") int page, @Query("per_page") int perPageCount);

    //Author info
    @GET("wp-json/wp/v2/users/{id}")
    Call<AuthorInfo> getAuthorInfo(@Path("id") int id);

}
