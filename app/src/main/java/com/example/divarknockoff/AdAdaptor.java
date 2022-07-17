package com.example.divarknockoff;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.divarknockoff.model.AdObject;
import com.example.divarknockoff.model.Repository;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class AdAdaptor extends RecyclerView.Adapter<AdAdaptor.AdViewHolder>{
    private List<AdObject> adObjectList;

    public AdAdaptor(List<AdObject> adObjectList) {
        this.adObjectList = adObjectList;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.layout_holder,parent,false);
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        holder.bind(adObjectList.get(position));
    }

    @Override
    public int getItemCount() {
        return adObjectList == null ? 0 : adObjectList.size();
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView titleTextView;
        private MaterialTextView cityTextView;
        private ImageView imageViewVIP;
        private MenuItem menuItemDelete;
        public AdViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView_title);
            cityTextView = itemView.findViewById(R.id.textView_city);
            imageViewVIP = itemView.findViewById(R.id.imageView_vip);
            menuItemDelete = itemView.findViewById(R.id.menu_delete);
        }

        public void bind(final AdObject adObject){
            titleTextView.setText(adObject.getTitle());
            cityTextView.setText(adObject.getCity());
            if (!adObject.isVip()){
                imageViewVIP.setVisibility(View.INVISIBLE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (menuItemDelete.isChecked()){
//                        try {
//                            Repository.getInstance(itemView.getContext()).deleteAdObject(adObject);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }else {
                        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                        DescriptionFragment descriptionFragment = DescriptionFragment.create(adObject.getId().toString());
                        UiUtil.changeFragment(activity.getSupportFragmentManager(), descriptionFragment);
//                    }
                }
            });
        }
    }
}
