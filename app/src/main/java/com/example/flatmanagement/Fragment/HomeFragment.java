package com.example.flatmanagement.Fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flatmanagement.Database.Dbhelper;
import com.example.flatmanagement.Model.House;
import com.example.flatmanagement.Adapter.HouseAdapter;
import com.example.flatmanagement.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private House house;
    private HouseAdapter adapter;
    private Context context;
    private Dbhelper dbhelper;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        showHouseInList(view);








        return view;
    }

    private void showHouseInList(View view) {
        ArrayList houseList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new HouseAdapter(context,houseList);
        recyclerView.setAdapter(adapter);



        Cursor cursor=dbhelper.getHouseInfo();

        while (cursor.moveToNext()){

            String houseName=cursor.getString(cursor.getColumnIndex(dbhelper.KEY_HOUSE_NAME));
            int totalFlat=cursor.getInt(cursor.getColumnIndex(dbhelper.KEY_TOTAL_FLAT));
            String managerName=cursor.getString(cursor.getColumnIndex(dbhelper.KEY_MANAGER_NAME));
            String managerContact=cursor.getString(cursor.getColumnIndex(dbhelper.KEY_MANAGER_CONTACT));

            houseList.add(new House(houseName,totalFlat,managerName,managerContact));

            adapter.notifyDataSetChanged();

        }
    }

    private void init(View view) {
        recyclerView=view.findViewById(R.id.recyclearView);
        dbhelper=new Dbhelper(getContext());





    }

}
