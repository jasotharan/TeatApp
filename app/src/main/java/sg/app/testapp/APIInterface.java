package sg.app.testapp;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sg.app.testapp.model.User;

/**
 * Created by anupamchugh on 09/01/17.
 */

interface APIInterface {


    @GET("/photos")
    Call<List<User>> getAllPhotos();

//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);

}
