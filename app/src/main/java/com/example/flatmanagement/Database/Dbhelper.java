package com.example.flatmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.flatmanagement.Model.FlatInfo;
import com.example.flatmanagement.Model.House;
import com.example.flatmanagement.Model.Member;

public class Dbhelper extends SQLiteOpenHelper {
    private Context context;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "E-Home";
    public static final String TABLE_FLAT = "FLAT_INFO";
    public static final String KEY_ID = "id";
    public static final String KEY_FLAT_NO = "flat_no";
    public static final String KEY_HOUSE_NO = "house_no";
    public static final String KEY_FLAT_RENT = "flat_rent";
    public static final String KEY_GAS_BILL = "gas_bill";
    public static final String KEY_ELECTRIC_BILL = "electric_bill";
    public static final String KEY_WASHMAN = "washman_bill";
    public static final String KEY_SERVICE = "service_charge";

    public static final String MEMBER_TABLE = "MEMBER_INFO";
    public static final String KEY_MEMBER_ID = "id";
    public static final String KEY_MEMBER_NAME = "name";
    public static final String KEY_MEMBER_PHONE = "phone";
    public static final String KEY_MEMBER_EMAIL = "email";
    public static final String KEY_MEMBER_PERMANENT_ADDRESS = "permanent_address";


    public static final String HOUSE_TABLE = "HOUSE_INFO";
    public static final String KEY_HOUSE_ID = "id";
    public static final String KEY_HOUSE_NAME = "houseName";
    public static final String KEY_TOTAL_FLAT = "totalFlat";
    public static final String KEY_MANAGER_NAME = "managerName";
    public static final String KEY_MANAGER_CONTACT = "managerContact";



    public Dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FLAT_INFO_TABLE = "CREATE TABLE " + TABLE_FLAT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                +KEY_FLAT_NO + " TEXT,"
                +KEY_HOUSE_NO + " TEXT,"
                +KEY_FLAT_RENT + " INTEGER,"
                +KEY_GAS_BILL + " INTEGER,"
                +KEY_ELECTRIC_BILL + " INTEGER,"
                +KEY_WASHMAN + " INTEGER,"
                +KEY_SERVICE + " INTEGER" + ")";


        String CREATE_HOUSE_INFO_TABLE = " CREATE TABLE " + HOUSE_TABLE + "("
                + KEY_HOUSE_ID + " INTEGER PRIMARY KEY,"
                +KEY_HOUSE_NAME + " TEXT,"
                +KEY_TOTAL_FLAT + " TEXT,"
                +KEY_MANAGER_NAME + " TEXT,"
                +KEY_MANAGER_CONTACT + " TEXT" + ")";


        db.execSQL(CREATE_HOUSE_INFO_TABLE);
        db.execSQL(CREATE_FLAT_INFO_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Adding new Flat info
    void addFlatInfo(FlatInfo flatInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FLAT_NO, flatInfo.getFlat_no().trim());
        values.put(KEY_HOUSE_NO, flatInfo.getHouse_no().trim());
        values.put(KEY_ELECTRIC_BILL, flatInfo.getElectric_bill());
        values.put(KEY_GAS_BILL, flatInfo.getGass_bill());
        values.put(KEY_WASHMAN, flatInfo.getWashman_bill());
        values.put(KEY_FLAT_RENT, flatInfo.getFlat_rent());
        values.put(KEY_SERVICE, flatInfo.getService_charge());

        // Inserting Row
        db.insert(TABLE_FLAT, null, values);
        db.close(); // Closing database connection
    }


    // Adding new Flat info
    public void addHouseInfo(House houseInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOUSE_NAME,houseInfo.getHouseName());
        values.put(KEY_TOTAL_FLAT,houseInfo.getTotalFlat());
        values.put(KEY_MANAGER_NAME,houseInfo.getManagerName());
        values.put(KEY_MANAGER_CONTACT,houseInfo.getManagerContact());


        // Inserting Row
        db.insert(HOUSE_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public Cursor getHouseInfo(){
        //get all data form database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+HOUSE_TABLE,null);
        return cursor;

    }
    public boolean deleteHouse(String houseName){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(HOUSE_TABLE, KEY_HOUSE_NAME + " =? ", new String[]{houseName}) > 0;
    }



    long addMemberInfo(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEMBER_NAME, member.getName());
        values.put(KEY_MEMBER_PHONE, member.getPhone());
        values.put(KEY_MEMBER_EMAIL, member.getEmail());
        values.put(KEY_MEMBER_PERMANENT_ADDRESS, member.getPermanent_address());


        // Inserting Row
        long id=db.insert(MEMBER_TABLE, null, values);

        db.close(); // Closing database connection
        return id;
    }

    public boolean isFlatExist(String flat_no,String house_no) {
        SQLiteDatabase db =  getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM FLAT_INFO WHERE flat_no=? AND house_no=?", new String[] { flat_no, house_no });
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public boolean isMemberExist(String member_name,String member_phone) {
        SQLiteDatabase db =  getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM MEMBER_INFO WHERE name=? AND phone=?", new String[] { member_name, member_phone });

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public Cursor TotalRent(String FlatNO,String HouseNo){
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT Sum(electric_bill) + Sum(gas_bill)+ Sum(service_charge) + Sum(flat_rent) + Sum(washman_bill) AS T FROM  FLAT_INFO WHERE flat_no=? AND house_no=?", new String[] { FlatNO, HouseNo });
        return cursor;
    }



}
