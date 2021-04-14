package com.example.cursoandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDeDatos (context:Context, nombre:String, factory:SQLiteDatabase?, version:Int):SQLiteOpenHelper(context, nombre, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db.execSQL("create table usuarios(nombre text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, versionAnterior: Int, nuevaVersion: Int) {
    }
}