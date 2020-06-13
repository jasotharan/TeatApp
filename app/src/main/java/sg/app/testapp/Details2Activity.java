package sg.app.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import sg.app.testapp.model.User;

public class Details2Activity extends AppCompatActivity {
    private Context mContext;
    private User selectedUser;
    private Button saveUser;
    private ImageView userImageView;
    private TextView userLongText,toolbar_edit,toolbar_cancel;
    private EditText userLongEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;
        Bundle bundle = getIntent().getExtras();
        selectedUser = (User) bundle.getSerializable("selectedUser");
        initUIComponents();

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Title");
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initUIComponents() {
        toolbar_edit = findViewById(R.id.toolbar_edit);
        toolbar_cancel = findViewById(R.id.toolbar_cancel);
        userImageView = findViewById(R.id.user_image_view);
        saveUser = findViewById(R.id.save_user);
        userLongText = findViewById(R.id.user_long_text);
        userLongEditText = findViewById(R.id.user_long_edit_text);
        userLongText.setText(selectedUser.getTitle());
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
        builder.build().load(selectedUser.getUrl())
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

    } public void saveCancel(View v) {
        // does something very interesting
       // Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
        userLongEditText.setVisibility(View.GONE);
        saveUser.setVisibility(View.GONE);
        userLongText.setVisibility(View.VISIBLE);
        toolbar_edit.setVisibility(View.VISIBLE);
        toolbar_cancel.setVisibility(View.GONE);

    }
}
