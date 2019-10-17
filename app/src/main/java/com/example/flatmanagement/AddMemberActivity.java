package com.example.flatmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;

public class AddMemberActivity extends AppCompatActivity {

    private EditText nameEt,phoneEt,emailEt,permanent_addressEt;
    private Button btn1_submit;
    private Member member;

    private String name,phone,email,permanent_address;
    private Dbhelper dbhelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        init();

        dbhelper=new Dbhelper(this);
        btn1_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()){
                    Toast.makeText(AddMemberActivity.this, "you must enter the valid info", Toast.LENGTH_SHORT).show();
                }
                else{
                    addMemeberToTheDatabase();
                }
            }
        });
    }
    private void addMemeberToTheDatabase() {
        member=new Member(name,phone,email,permanent_address);

        long id=dbhelper.addMemberInfo(member);
        Toast.makeText(AddMemberActivity.this,"MEMBER ADDED  "+id, Toast.LENGTH_SHORT).show();

    }

    private boolean validate() {
        name=nameEt.getText().toString().trim();
        phone=phoneEt.getText().toString().trim();
        email=emailEt.getText().toString().trim();
        permanent_address=permanent_addressEt.getText().toString().trim();
        boolean valid;
        valid=true;

        if(name.equals("") || !name.matches("[a-zA-Z.? ]*")){
            nameEt.setError("use [a-z] or number for defining your name");
            valid=false;
        }else{
            nameEt.setError(null);
            valid=true;
        }
        if(phone.equals("") || phone.length()>11 || phone.length()<11){
            phoneEt.setError("use valid phone");
            valid=false;
        }else{
            phoneEt.setError(null);
            valid=true;
        }

        if(permanent_address.equals("")){
            permanent_addressEt.setError(" address cant be null");
            valid=false;
        }
        else{
            valid=true;
        }
        return valid;
    }




    private void init() {
        nameEt=findViewById(R.id.member_name);
        phoneEt=findViewById(R.id.member_phone);
        emailEt=findViewById(R.id.member_email);
        permanent_addressEt=findViewById(R.id.member_permanent_address);
        btn1_submit=findViewById(R.id.btn1_submit);

    }
}