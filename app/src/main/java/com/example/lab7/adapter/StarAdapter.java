package com.example.lab7.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lab7.R;
import com.example.lab7.beans.Star;
import com.example.lab7.service.StarService;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private Context context;
    private List<Star> stars;
    private List<Star> starsFilter;
    private NewFilter mFilter;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
        this.mFilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        final StarViewHolder holder = new StarViewHolder(v);

        holder.itemView.setOnClickListener(view -> showEditPopup(view, holder));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star s = starsFilter.get(position);
        holder.name.setText(s.getName().toUpperCase());
        holder.stars.setRating(s.getStar());
        holder.idss.setText(String.valueOf(s.getId()));

        // Chargement d'une ressource locale (int) au lieu d'une URL (String)
        Glide.with(context)
            .load(s.getImg()) 
            .apply(new RequestOptions()
                .centerCrop()
                .placeholder(android.R.drawable.ic_menu_gallery)
            )
            .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return starsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private void showEditPopup(View itemView, StarViewHolder holder) {
        View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);

        CircleImageView popupImg = popup.findViewById(R.id.img);
        RatingBar popupBar = popup.findViewById(R.id.ratingBar);
        TextView popupIdss = popup.findViewById(R.id.idss);

        try {
            Bitmap bitmap = ((BitmapDrawable) ((CircleImageView) itemView.findViewById(R.id.img)).getDrawable()).getBitmap();
            popupImg.setImageBitmap(bitmap);
        } catch (Exception e) {}

        popupBar.setRating(((RatingBar) itemView.findViewById(R.id.stars)).getRating());
        popupIdss.setText(((TextView) itemView.findViewById(R.id.ids)).getText().toString());

        new AlertDialog.Builder(context)
            .setTitle("⭐ " + context.getString(R.string.rating_title))
            .setMessage(context.getString(R.string.rating_message))
            .setView(popup)
            .setPositiveButton(R.string.btn_validate, (dialog, which) -> {
                float newRating = popupBar.getRating();
                int starId = Integer.parseInt(popupIdss.getText().toString());

                Star star = StarService.getInstance().findById(starId);
                if (star != null) {
                    star.setStar(newRating);
                    StarService.getInstance().update(star);
                    notifyItemChanged(holder.getAdapterPosition());
                }
            })
            .setNegativeButton(R.string.btn_cancel, null)
            .create()
            .show();
    }

    static class StarViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView idss;
        TextView name;
        RatingBar stars;

        StarViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            idss = itemView.findViewById(R.id.ids);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
        }
    }

    public class NewFilter extends Filter {
        private RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Star> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(stars);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Star star : stars) {
                    if (star.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(star);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter = (List<Star>) filterResults.values;
            mAdapter.notifyDataSetChanged();
        }
    }
}