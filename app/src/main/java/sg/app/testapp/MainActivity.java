package sg.app.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.app.testapp.model.Article;


public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<List<Article>> call = apiInterface.getAllArticle();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    generateDataList(response.body());

                    // save list in local session
                    CommonApi.saveStoresList(mContext, response.body());

                } else if  (CommonApi.getStoresList(mContext)!=null){
                    // network response null show last save data
                    Toast.makeText(MainActivity.this, "showing local session data", Toast.LENGTH_SHORT).show();
                    generateDataList(CommonApi.getStoresList(mContext));
                }else {
                    // get mock data and show for sample app
                    Toast.makeText(MainActivity.this, "showing mock for test app", Toast.LENGTH_SHORT).show();
                    generateDataList(getSampleList());
                    CommonApi.saveStoresList(mContext, getSampleList());
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Article> articleList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, articleList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Article> getSampleList() {
        String json = null;
        try {
            InputStream inputStream = mContext.getAssets().open("sampleList.json");
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
}
