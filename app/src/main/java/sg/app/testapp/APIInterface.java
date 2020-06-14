package sg.app.testapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sg.app.testapp.model.Article;
import sg.app.testapp.model.LongArticle;

/**
 * Created by anupamchugh on 09/01/17.
 */

interface APIInterface {


    @GET("/photos")
    Call<List<Article>> getAllArticle();

    @GET("/article?")
    Call<LongArticle> getSelectedArticle(@Query("id") String id);

}
