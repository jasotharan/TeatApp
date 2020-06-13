package sg.app.testapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sg.app.testapp.model.Article;


public class CommonApi {
    public static final String SHARED_PREFERENCE = "shared_preference";
    public static final String USER_LIST = "user_list";


    public static void saveStoresList(Context context, List<Article> articleList) {
        SharedPreferences myPrefs = context.getSharedPreferences(SHARED_PREFERENCE, 0);
        SharedPreferences.Editor prefEditor = myPrefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(articleList);
        prefEditor.putString(USER_LIST, json);
        prefEditor.apply();
    }

    public static  ArrayList<Article>  getStoresList(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(SHARED_PREFERENCE, 0);
        String articalList = myPrefs.getString(USER_LIST, null);
        if (articalList == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Article>>() {
        }.getType();
        return gson.fromJson(articalList, type);
    }

    public static void removeStoresList(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(SHARED_PREFERENCE, 0);
        SharedPreferences.Editor myPrefsEditor = myPrefs.edit();
        myPrefsEditor.remove(USER_LIST);
        myPrefsEditor.apply();

    }
}