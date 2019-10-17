package com.example.flatmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AssignActivity extends AppCompatActivity {
    private EditText dateEt,advanceEt;
    public Spinner sflat_id,shouse_id,smember_id;
    private String fid,hid,mid;
    private Dbhelper dbhelper;
    private String Flat_name,House_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        init();

        dbhelper=new Dbhelper(this);
        Intent intent=getIntent();
        Flat_name=intent.getStringExtra("Flat_name");
        House_name=intent.getStringExtra("House_name");

        Toast.makeText(this, ""+Flat_name+"  "+House_name, Toast.LENGTH_SHORT).show();
        dateEt.setText(Flat_name);


        getDataForSpinner();





    }

    private void getDataForSpinner() {


    }

    private void addItemOnFlatIdSpinner(String fid) {
        ArrayList<String> list=new ArrayList<>();
        list.add(fid);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(AssignActivity.this,android.R.layout.simple_spinner_dropdown_item,list);
        sflat_id.setAdapter(adapter);
    }
    private void addItemOnHouseIdSpinner(String hid) {
        ArrayList<String> list=new ArrayList<>();
        list.add(hid);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(AssignActivity.this,android.R.layout.simple_spinner_dropdown_item,list);
        shouse_id.setAdapter(adapter);
    }
    private void addItemOnMemberIdSpinner(String mid) {
        ArrayList<String> list=new ArrayList<>();
        list.add(mid);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(AssignActivity.this,android.R.layout.simple_spinner_dropdown_item,list);
        smember_id.setAdapter(adapter);
    }

    private void init() {
        dateEt=findViewById(R.id.dateEt);
        advanceEt=findViewById(R.id.advanceEt);
        sflat_id=findViewById(R.id.flat_id);
        shouse_id=findViewById(R.id.house_id);
        smember_id=findViewById(R.id.member_id);
    }
}