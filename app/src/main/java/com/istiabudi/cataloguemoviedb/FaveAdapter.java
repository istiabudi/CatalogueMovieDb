package com.istiabudi.cataloguemoviedb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.istiabudi.cataloguemoviedb.db.DatabaseContract.contentUri;


public class FaveAdapter extends RecyclerView.Adapter<FaveAdapter.ViewHolder>{

    final static String EXTRA_MOVIE = "movie";
    ArrayList<MovieItem> listMovie = new ArrayList<>();

    public FaveAdapter(Context context) {
        this.context = context;
    }

    Context context;

    @Override
    public FaveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_fave_item, parent, false);
        return new ViewHolder(itemRow);
    }


    @Override
    public void onBindViewHolder(@NonNull FaveAdapter.ViewHolder holder, final int position) {
        Glide.with(context)
                .load(listMovie.get(position)
                        .getImage())
                .override(640, 480).into(holder.poster);
        Log.d(getClass().getSimpleName(), "onBindViewHolder: " +listMovie.get(position).getImage());
        holder.title.setText(listMovie.get(position).getTitle());
        holder.releaseDate.setText(listMovie.get(position).getDate());
        holder.remarks.setText(listMovie.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        Log.d(getClass().getSimpleName(), "getItemCount: " + listMovie.size());
        return listMovie.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title, remarks, releaseDate;
        Button btnDetail;

        @BindView(R.id.btn_Delete)
        Button buttonDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            poster = itemView.findViewById(R.id.img_item_photo);
            title = itemView.findViewById(R.id.tv_item_name);
            remarks = itemView.findViewById(R.id.tv_item_remarks);
            releaseDate = itemView.findViewById(R.id.tv_date);
            btnDetail = itemView.findViewById(R.id.btn_detail);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(contentUri() + "/" + listMovie.get(getAdapterPosition()).getId());
                    context.getContentResolver().delete(uri, null, null);
                    listMovie.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                }
            });

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(EXTRA_MOVIE, (Parcelable) listMovie.get(getAdapterPosition()) );
                    context.startActivity(intent);

                }
            });

        }
    }

    public ArrayList<MovieItem> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<MovieItem> listMovie) {
        this.listMovie = listMovie;
    }

}

