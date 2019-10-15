package com.example.flatmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class AddFlatActivity extends AppCompatActivity {

    private EditText flat_no,house_no,flat_fee,gas_bill,electric_bill,washmen_bill,others;
    private TextView total;
    private Button submit_button;

    private String Flat_no,House_no;
    private  int Flat_fee,Gas_bill,Electric_bill,Washmen_bill,Others;
    private Context context;
    private FlatInfo flatInfo;
    private Dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Adding Flat Info");
        init();


        dbhelper=new Dbhelper(this);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettingTheValues();
                addingFlatInfoToTheDatabase();
            }
        });

        // Intent intent=new Intent(AddFlatActivity.this,AddMemberActivity.class);
        //startActivity(intent);
    }



    private void addingFlatInfoToTheDatabase() {
        if(!validate()){
            Toast.makeText(this, "Please Provide Valid Data", Toast.LENGTH_SHORT).show();
        }else{
            //for adding the data
            flatInfo=new FlatInfo(Flat_no,House_no,Flat_fee,Gas_bill,Electric_bill,Washmen_bill,Others);
            dbhelper.addFlatInfo(flatInfo);

            Cursor cursor= dbhelper.TotalRent(Flat_no,House_no);
            cursor.moveToFirst();
            int TotalRent = cursor.getInt(cursor.getColumnIndex("T"));
            total.setText(String.valueOf(TotalRent));
        }
    }

    private boolean validate() {
        boolean valid;
        valid=true;

        if(Flat_no.equals("") || !Flat_no.matches("[a-zA-Z0-9/.? ]*")){
            flat_no.setError("use [a-z] or [A-Z] or number for defining your flat no");
            valid=false;
        }else{
            valid=true;
        }
        if(House_no.equals("") || !House_no.matches("[a-zA-Z0-9/.? ]*")){
            house_no.setError("use [a-z] or [A-Z] or number for defining your flat no");
            valid=false;
        }else{
            valid=true;
        }

        return valid;
    }


    private void gettingTheValues() {
        Flat_no=flat_no.getText().toString();
        House_no=house_no.getText().toString();
        Gas_bill=Integer.valueOf(gas_bill.getText().toString().trim());
        Flat_fee=Integer.valueOf(flat_fee.getText().toString().trim());
        Electric_bill=Integer.valueOf(electric_bill.getText().toString().trim());
        Washmen_bill=Integer.valueOf(washmen_bill.getText().toString().trim());
        Others=Integer.valueOf(others.getText().toString().trim());
    }

    private void init() {
        flat_no=findViewById(R.id.flat_no);
        house_no=findViewById(R.id.house_no);
        flat_fee=findViewById(R.id.flat_fee);
        gas_bill=findViewById(R.id.gas_bill);
        electric_bill=findViewById(R.id.electric_bill);
        washmen_bill=findViewById(R.id.washman_bill);
        others=findViewById(R.id.others);
        total=findViewById(R.id.total);
        submit_button=findViewById(R.id.btn_submit);
    }

}