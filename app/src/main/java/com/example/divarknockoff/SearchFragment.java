package com.example.divarknockoff;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divarknockoff.model.AdObject;
import com.example.divarknockoff.model.Repository;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    private EditText et_search;
    private RecyclerView mRecyclerView;
    private List<AdObject> adList;
    private AdvertAdapter advertAdapter;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mTaskLab = TaskLab.getmInstance(getActivity());

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        et_search = view.findViewById(R.id.editText_search);
        mRecyclerView = view.findViewById(R.id.rcycler_search);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adList = Repository.getInstance(getContext()).getAdObjects();
        Log.i("searchh", "onCreateView: " + adList.size());

        if (advertAdapter == null)
            advertAdapter = new AdvertAdapter(adList, getActivity());
        mRecyclerView.setAdapter(advertAdapter);


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                updateUI(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateUI(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateUI(s.toString());
            }
        });
        return view;
    }


    private void updateUI(String s) {
        if (s.equals(""))
            adList = Repository.getInstance(getContext()).getAdObjects();
        else
            adList = Repository.getInstance(getContext()).searchTask(s);
        advertAdapter.setText(s);
        advertAdapter.setTaskList(adList);
        advertAdapter.notifyDataSetChanged();
    }


    public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AdvertHolder> {

        private List<AdObject> adList;
        private Context mContext;
        private String text = "";


        public AdvertAdapter(List<AdObject> adList, Context context) {
            adList = adList;
            mContext = context;
        }

        @NonNull
        @Override
        public SearchFragment.AdvertAdapter.AdvertHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.layout_holder, parent, false);
            AdvertHolder taskHolder = new AdvertHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SearchFragment.AdvertAdapter.AdvertHolder holder, int position) {
            AdObject adObject = adList.get(position);
            holder.bind(adObject);

        }

        @Override
        public int getItemCount() {
            return adList.size();
        }

        public void setTaskList(List<AdObject> adList) {
            this.adList = adList;
        }

        public void setText(String text) {
            this.text = text;
        }


        public class AdvertHolder extends RecyclerView.ViewHolder {
            private MaterialTextView titleTextView;
            private MaterialTextView cityTextView;
            private ImageView imageViewVIP;
            private MenuItem menuItemDelete;


            public AdvertHolder(View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.textView_title);
                cityTextView = itemView.findViewById(R.id.textView_city);
                imageViewVIP = itemView.findViewById(R.id.imageView_vip);
                menuItemDelete = itemView.findViewById(R.id.menu_delete);

            }

            public void bind(final AdObject adObject) {
                titleTextView.setText(adObject.getTitle());
                cityTextView.setText(adObject.getCity());
                if (!adObject.isVip()){
                    imageViewVIP.setVisibility(View.INVISIBLE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                        DescriptionFragment descriptionFragment = DescriptionFragment.create(adObject.getId().toString());
                        UiUtil.changeFragment(activity.getSupportFragmentManager(), descriptionFragment);
                    }
                });
            }

            private void bold(String sentence, String text,TextView textView) {
                if (!text.equals("")) {
                    if (sentence.contains(text)) {
                        int start = sentence.indexOf(text);
                        int end = start + text.length();
                        SpannableStringBuilder fancySentence = new SpannableStringBuilder(sentence);
                        fancySentence.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        textView.setText(fancySentence);
                    }
                }
            }
        }
    }

}
