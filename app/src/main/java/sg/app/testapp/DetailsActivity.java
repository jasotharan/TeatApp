package sg.app.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.app.testapp.model.Article;
import sg.app.testapp.model.LongArticle;
import sg.app.testapp.util.StringUtil;

public class DetailsActivity extends AppCompatActivity {
    private Context mContext;
    private Article selectedArticle;
    private Button saveUser;
    private ImageView userImageView;
    private TextView userLongText, toolbar_edit, toolbar_cancel;
    private EditText userLongEditText;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(DetailsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        mContext = this;
        Bundle bundle = getIntent().getExtras();
        selectedArticle = (Article) bundle.getSerializable("selectedArticle");
        initUIComponents();

        /*Create handle for the RetrofitInstance interface*/
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<LongArticle> call = apiInterface.getSelectedArticle("2");
        call.enqueue(new Callback<LongArticle>() {
            @Override
            public void onResponse(Call<LongArticle> call, Response<LongArticle> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    showLongDescription(response.body());
                } else {
                    showLongDescription(StringUtil.getSampleData(mContext));
                    Toast.makeText(DetailsActivity.this, "no response and this is sample data ...Please try later!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LongArticle> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(selectedArticle.getTitle());
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void showLongDescription(LongArticle longArticle) {
        userLongText.setText(longArticle.getText());
    }

    private void initUIComponents() {
        toolbar_edit = findViewById(R.id.toolbar_edit);
        toolbar_cancel = findViewById(R.id.toolbar_cancel);
        userImageView = findViewById(R.id.user_image_view);
        saveUser = findViewById(R.id.save_user);
        userLongText = findViewById(R.id.user_long_text);
        userLongEditText = findViewById(R.id.user_long_edit_text);
        userLongText.setText(selectedArticle.getShort_description());
        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLongEditText.setVisibility(View.GONE);
                saveUser.setVisibility(View.GONE);
                userLongText.setVisibility(View.VISIBLE);
                userLongText.setText(userLongEditText.getText());
                toolbar_edit.setVisibility(View.VISIBLE);
                toolbar_cancel.setVisibility(View.GONE);

            }
        });
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(selectedArticle.getAvatar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(userImageView);
    }

    public void editUser(View v) {
        // does something very interesting
        //  Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
        userLongEditText.setVisibility(View.VISIBLE);
        saveUser.setVisibility(View.VISIBLE);
        userLongText.setVisibility(View.GONE);
        toolbar_edit.setVisibility(View.GONE);
        toolbar_cancel.setVisibility(View.VISIBLE);
        userLongEditText.setText(userLongText.getText());

    }

    public void saveCancel(View v) {
        // does something very interesting
        // Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
        userLongEditText.setVisibility(View.GONE);
        saveUser.setVisibility(View.GONE);
        userLongText.setVisibility(View.VISIBLE);
        toolbar_edit.setVisibility(View.VISIBLE);
        toolbar_cancel.setVisibility(View.GONE);

    }


}
