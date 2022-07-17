package com.example.divarknockoff;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divarknockoff.model.AdObject;
import com.example.divarknockoff.model.Repository;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAdFragment extends Fragment {

    private TextInputEditText editTextTitle;
    private TextInputEditText editTextContent;
    private TextInputEditText editTextCity;
    private TextInputEditText editTextPhone;
    private MaterialCheckBox checkBoxVip;
    private MaterialButton buttonCreate;

    public CreateAdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_ad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextTitle = view.findViewById(R.id.editText_title);
        editTextContent = view.findViewById(R.id.editText_content);
        editTextCity = view.findViewById(R.id.editText_city);
        editTextPhone = view.findViewById(R.id.editText_phone_number);
        checkBoxVip = view.findViewById(R.id.checkBox_vip);
        buttonCreate = view.findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAd();
            }
        });

    }

    private void createAd(){
        AdObject adObject = AdObject.AdBuilder.anAdObject()
                .title(editTextTitle.getText().toString())
                .content(editTextContent.getText().toString())
                .city(editTextCity.getText().toString())
                .phone(editTextPhone.getText().toString())
                .vip(checkBoxVip.isChecked())
                .id(UUID.randomUUID())
                .userId(Repository.getInstance(getContext()).getCurrentUser().getAccountId())
                .build();
        Repository.getInstance(getContext()).insertAdObject(adObject);
        getFragmentManager().popBackStack();
    }
}
