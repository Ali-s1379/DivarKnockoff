package com.example.divarknockoff;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.divarknockoff.model.AdObject;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class AdAdaptorHorizontal extends RecyclerView.Adapter<AdAdaptorHorizontal.AdViewHolderHorizontal>{
    private List<AdObject> adObjectList;

    public AdAdaptorHorizontal(List<AdObject> adObjectList) {
        this.adObjectList = adObjectList;
    }

    @NonNull
    @Override
    public AdViewHolderHorizontal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.layout_holder_horizontal,parent,false);
        return new AdViewHolderHorizontal(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolderHorizontal holder, int position) {
        holder.bind(adObjectList.get(position));
    }

    @Override
    public int getItemCount() {
        return adObjectList == null ? 0 : adObjectList.size();
    }

    public static class AdViewHolderHorizontal extends RecyclerView.ViewHolder {
        private MaterialTextView titleTextView;
        private MaterialTextView cityTextView;
        public AdViewHolderHorizontal(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView_title2);
            cityTextView = itemView.findViewById(R.id.textView_city2);
        }

        public void bind(final AdObject adObject){
            titleTextView.setText(adObject.getTitle());
            cityTextView.setText(adObject.getCity());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                    DescriptionFragment descriptionFragment = DescriptionFragment.create(adObject.getId().toString());
                    UiUtil.changeFragment(activity.getSupportFragmentManager(),descriptionFragment);
                }
            });
        }
    }
}
