package com.example.myapplication.network

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, dbname, factory, version) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(
            /* sql = */
            "create table user (id integer primary key autoincrement," +
                    "name varchar(100),mobile varchar(10),password varchar(20),gender varchar(20))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertUserData(name:String,mobile:String,pass:String,gender:String){
        val db:SQLiteDatabase=writableDatabase
        val values=ContentValues()
        values.put("name",name)
        values.put("mobile",mobile)
        values.put("password",pass)
        values.put("gender",gender)

        db.insert("user",null,values)
        db.close()
    }

    fun userPresent(mobile:String,password: String):Boolean{
        val db=writableDatabase
        val query="select * from user where mobile='$mobile' and password='$password'"
        val cursor=db.rawQuery(query,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
    companion object {
        internal const val dbname = "userDB"
        internal val factory = null
        internal const val version = 1
    }


}