package com.example.divarknockoff;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.material.button.MaterialButton;

import com.example.divarknockoff.model.AdObject;
import com.example.divarknockoff.model.Repository;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setTitle("Delete All Advertisement?");
                builder.setMessage("Warning: The Change Is Permanent");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Repository.getInstance(getContext()).deleteTable();
                                getFragmentManager().popBackStack();
                                UiUtil.changeFragment(getFragmentManager(),new MainFragment());
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
//                if (item.isChecked()){
//                    item.setChecked(false);
//                }else item.setChecked(true);
////                if (item.isChecked()){
//                    Repository.getInstance(getContext()).deleteAdObject();
//                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton createButton = view.findViewById(R.id.button_ad_create);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UiUtil.changeFragment(getActivity().getSupportFragmentManager()
                ,new CreateAdFragment());
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.main_recycler_view);
        List<AdObject> list = Repository.getInstance(getContext()).getAdObjects();
        AdAdaptor adAdaptor = new AdAdaptor(list);
        recyclerView.setAdapter(adAdaptor);
        RecyclerView recyclerView2 = view.findViewById(R.id.vip_recycler_view);
        List<AdObject> list2 = new ArrayList<>();
        for (int i = 0;i < Repository.getInstance(getContext()).getAdObjects().size();i++){
            if (Repository.getInstance(getContext()).getAdObjects().get(i).isVip()){
                list2.add(Repository.getInstance(getContext()).getAdObjects().get(i));
            }
        }
        AdAdaptorHorizontal adAdaptor2 = new AdAdaptorHorizontal(list2);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setAdapter(adAdaptor2);
        recyclerView2.setLayoutManager(layoutManager);
    }
}
