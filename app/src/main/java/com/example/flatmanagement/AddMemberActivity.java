package com.example.flatmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                name=nameEt.getText().toString();
                phone=phoneEt.getText().toString().trim();
                email=emailEt.getText().toString().trim();
                permanent_address=permanent_addressEt.getText().toString();



                member=new Member(name,phone,email,permanent_address);
                long id=dbhelper.addMemberInfo(member);


                Toast.makeText(AddMemberActivity.this,"MEMBER ADDED  "+id, Toast.LENGTH_SHORT).show();



            }
        });




    }




    private void init() {
        nameEt=findViewById(R.id.member_name);
        phoneEt=findViewById(R.id.member_phone);
        emailEt=findViewById(R.id.member_email);
        permanent_addressEt=findViewById(R.id.member_permanent_address);
        btn1_submit=findViewById(R.id.btn1_submit);

    }
}
