package sg.app.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        TextView txtTitle;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.userNameTView);
            coverImage = mView.findViewById(R.id.userImageView);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());

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
