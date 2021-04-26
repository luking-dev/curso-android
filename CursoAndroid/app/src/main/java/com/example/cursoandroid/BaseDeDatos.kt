package com.example.cursoandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class BaseDeDatos (context: Context) :SQLiteOpenHelper(context, NOMBRE_BD, null, VERSION) {

    companion object {
        val NOMBRE_BD = "usuarios"
        val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {

        // creo tabla usuarios con 3 columnas
        db.execSQL("create table usuarios(id integer primary key autoincrement, nombre text, email text, clave text, avatar text)")

        // agrego 2 registros/rows/filas iniciales
        val consultaSQL = "insert into usuarios (nombre, email, clave, avatar)" +
                " values " +
                "('Juan Perez', 'juan@empresa.com', '7C4A8D09CA3762AF61E59520943DC26494F8941B', 'https://2.bp.blogspot.com/-bD9iB6eEFNc/WXdfMahDf-I/AAAAAAAAEX0/Jr2V9cPvrJITSZkTmwt2k1PBZ_m830A5wCLcBGAs/s1600/image1.png'), " +
                "('Maria Fernandez', 'maria@gmail.com', '7C4A8D09CA3762AF61E59520943DC26494F8941B', 'https://2.bp.blogspot.com/-bD9iB6eEFNc/WXdfMahDf-I/AAAAAAAAEX0/Jr2V9cPvrJITSZkTmwt2k1PBZ_m830A5wCLcBGAs/s1600/image1.png')"
        db.execSQL(consultaSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, versionAnterior: Int, nuevaVersion: Int) {
    }

    override fun onDowngrade(db: SQLiteDatabase, versionAnterior: Int, nuevaVersion: Int) {
        db.version = versionAnterior
    }
}