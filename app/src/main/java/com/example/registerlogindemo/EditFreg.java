package com.example.registerlogindemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EditFreg extends Fragment {

    private static final String ARG_USER_ID = "user_id";
    private String userId;

    Button addcar;

    public static EditFreg newInstance(String userId) {
        EditFreg fragment = new EditFreg();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    public EditFreg() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_freg, container, false);

        // Find the button within the inflated layout
        addcar = rootView.findViewById(R.id.addcar);
        addcar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddCarActivity.class);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
