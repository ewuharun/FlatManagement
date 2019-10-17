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
    private  String Flat_fee,Gas_bill,Electric_bill,Washmen_bill,Others;
    private Context context;
    private FlatInfo flatInfo;
    private Dbhelper dbhelper;

    private Button demoAssignBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Adding Flat Info");
        init();
        demoAssignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        dbhelper=new Dbhelper(this);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()){
                    Toast.makeText(AddFlatActivity.this, "You must provide valid data", Toast.LENGTH_SHORT).show();
                }
                else{
                    addingFlatInfoToTheDatabase();
                }

            }
        });


    }

    private void addingFlatInfoToTheDatabase() {
        //for adding the data
        flatInfo=new FlatInfo(Flat_no,House_no,Integer.valueOf(Flat_fee),Integer.valueOf(Gas_bill),Integer.valueOf(Electric_bill),Integer.valueOf(Washmen_bill),Integer.valueOf(Others));
        dbhelper.addFlatInfo(flatInfo);

        Cursor cursor= dbhelper.TotalRent(Flat_no,House_no);
        cursor.moveToFirst();
        int TotalRent = cursor.getInt(cursor.getColumnIndex("T"));
        total.setText(String.valueOf(TotalRent));
        Intent intent=new Intent(AddFlatActivity.this,AddMemberActivity.class);
        startActivity(intent);
    }

    private boolean validate() {
        Flat_no=flat_no.getText().toString().trim();
        House_no=house_no.getText().toString().trim();
        Gas_bill=gas_bill.getText().toString().trim();
        Flat_fee=flat_fee.getText().toString().trim();
        Electric_bill=electric_bill.getText().toString().trim();
        Washmen_bill=washmen_bill.getText().toString().trim();
        Others=others.getText().toString().trim();
        boolean valid;
        valid=true;

        if(Flat_no.equals("") || !Flat_no.matches("[a-zA-Z0-9/.? ]*")){
            flat_no.setError("use [a-z] or [A-Z] or number for defining your flat no");
            valid=false;
        }else{
            flat_no.setError(null);
            valid=true;
        }
        if(House_no.equals("") || !House_no.matches("[a-zA-Z0-9/.? ]*")){
            house_no.setError("use [a-z] or [A-Z] or number for defining your flat no");
            valid=false;
        }else{
            house_no.setError(null);
            valid=true;
        }
        if(Gas_bill.equals("")){
            gas_bill.setError("gas bill error");
            gas_bill.setText("0");
            valid=false;
        }else{
            gas_bill.setError(null);
            valid=true;
        }
        if(Electric_bill.equals("")){
            electric_bill.setError("electric bill error");
            electric_bill.setText("0");
            valid=false;
        }else{
            electric_bill.setError(null);
            valid=true;
        }
        if(Flat_fee.equals("")){
            flat_fee.setError("Rent bill error");
            flat_fee.setText("0");
            valid=false;
        }else{
            flat_fee.setError(null);
            valid=true;
        }
        if(Washmen_bill.equals("")){
            washmen_bill.setError("gas bill error");
            washmen_bill.setText("0");
            valid=false;
        }else{
            washmen_bill.setError(null);
            valid=true;
        }
        if(Others.equals("")){
            others.setError("gas bill error");
            others.setText("0");
            valid=false;
        }else{
            others.setError(null);
            valid=true;
        }

        return valid;
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
        demoAssignBtn=findViewById(R.id.assignEt);
    }

}
