package sg.app.testapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import sg.app.testapp.model.User;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<User> dataList;
    private Context context;

    public CustomAdapter(Context context,List<User> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle,userId,shortDescriptionTV;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.userNameTView);
            shortDescriptionTV = mView.findViewById(R.id.shortDescriptionTV);
            userId = mView.findViewById(R.id.userId);
            coverImage = mView.findViewById(R.id.userImageView);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item_list, parent, false);

        return new CustomViewHolder(view);
    }

    private void showDetailsPage(User user) {
        Intent intent = new Intent(context, Details2Activity.class);
        intent.putExtra("selectedUser", user);
        context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.userId.setText(dataList.get(position).getId().toString());
        holder.shortDescriptionTV.setText(dataList.get(position).getTitle().toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailsPage(dataList.get(position));
            }
        });
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
