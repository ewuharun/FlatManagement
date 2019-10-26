package com.example.flatmanagement.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.flatmanagement.Database.Dbhelper;
import com.example.flatmanagement.Model.House;
import com.example.flatmanagement.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateHouseFragment extends Fragment {

    private EditText houseNameEt,totalFlatEt,managerNameEt,managerContactEt;
    private Button addHouseBtn;
    private String houseName,totalFlat,managerName,managerContact;
    private House house;
    private Dbhelper dbhelper;



    public CreateHouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_house, container, false);
        init(view);
        addHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseName=houseNameEt.getText().toString();
                totalFlat=totalFlatEt.getText().toString();
                managerName=managerNameEt.getText().toString();
                managerContact=managerContactEt.getText().toString();
                house=new House(houseName,Integer.valueOf(totalFlat),managerName,managerContact);
                dbhelper.addHouseInfo(house);

            }
        });




        return view;
    }

    private void init(View view) {
        houseNameEt=view.findViewById(R.id.houseNameEt);
        totalFlatEt=view.findViewById(R.id.totalFlatEt);
        managerNameEt=view.findViewById(R.id.managerNameEt);
        managerContactEt=view.findViewById(R.id.managerContactEt);
        addHouseBtn=view.findViewById(R.id.addHouseBtn);
        dbhelper=new Dbhelper(view.getContext());

    }

}
