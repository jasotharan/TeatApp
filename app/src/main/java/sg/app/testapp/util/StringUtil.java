package sg.app.testapp.util;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import sg.app.testapp.model.Article;
import sg.app.testapp.model.LongArticle;

public class StringUtil {
    public static String millisToDate(String dateMilli  ) {
            String formattedDate = "";

        //creating Date from millisecond
        Date currentDate = new Date(Long.valueOf(dateMilli));

        //printing value of Date
        System.out.println("current Date: " + currentDate);

        DateFormat df = new SimpleDateFormat("YYYY-MM-DD -  HH:mm");

        //formatted value of current Date
        formattedDate = df.format(currentDate);
        System.out.println("Milliseconds to Date: " + formattedDate);

        return formattedDate;
    }

    public static ArrayList<Article> getSampleList(Context mContext,String finleNmae) {
        String json = null;
        try {
            InputStream inputStream = mContext.getAssets().open(finleNmae);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Article[] tags = gson.fromJson(json, Article[].class);
        ArrayList<Article> tagsList = new ArrayList<>();
        Collections.addAll(tagsList, tags);

        return tagsList;
    }

    public static LongArticle getSampleData(Context mContext) {
        String json = null;
        try {
            InputStream inputStream = mContext.getAssets().open("sampleLongArticle.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        LongArticle longArticle = gson.fromJson(json, LongArticle.class);
        return longArticle;
    }
}
