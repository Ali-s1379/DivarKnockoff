package com.example.divarknockoff;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.divarknockoff.model.Repository;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {
    public static final String ARGUMENT_ID = "ID";


    public static DescriptionFragment create(String id){
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_ID, String.valueOf(id));
        fragment.setArguments(bundle);
        return fragment;
    }

    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_description,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setTitle("Delete Current Advertisement?");
                builder.setMessage("Warning: The Change Is Permanent");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    Bundle bundle = getArguments();
                                    String id = bundle.getString(ARGUMENT_ID);
                                    Repository.getInstance(getContext()).deleteAdObject(Repository.getInstance(getContext()).getAdObject(UUID.fromString(id)));
                                    getFragmentManager().popBackStack();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        MaterialTextView textViewTitle = view.findViewById(R.id.textView_title3);
        MaterialTextView textViewContent = view.findViewById(R.id.textView_content);
        MaterialTextView textViewCity = view.findViewById(R.id.textView_city3);
        MaterialTextView textViewPhone = view.findViewById(R.id.textView_phone_number);
        MaterialCheckBox checkBoxVip = view.findViewById(R.id.checkBox_vip2);
        Bundle bundle = getArguments();
        String id = bundle.getString(ARGUMENT_ID);
        String title = Repository.getInstance(getContext()).getAdObject(UUID.fromString(id)).getTitle();
        String description = Repository.getInstance(getContext()).getAdObject(UUID.fromString(id)).getContent();
        String city = Repository.getInstance(getContext()).getAdObject(UUID.fromString(id)).getCity();
        String phone = Repository.getInstance(getContext()).getAdObject(UUID.fromString(id)).getPhone();
        boolean vip = Repository.getInstance(getContext()).getAdObject(UUID.fromString(id)).isVip();
        textViewTitle.setText(title);
        textViewContent.setText(description);
        textViewCity.setText(city);
        textViewPhone.setText(phone);
        checkBoxVip.setChecked(vip);
        return view;
    }

}
