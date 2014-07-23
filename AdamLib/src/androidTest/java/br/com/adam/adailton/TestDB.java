package br.com.adam.adailton;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.adam.adailton.lib.mainlibrary.DB.DB;

/**
 * Created by ad036950 on 23/07/2014.
 */
public class TestDB extends DB{



    private static String DATABASE_NAME = "teste.db";
    private static int DATABASE_VERSION = 1;



    public TestDB(Context context) {
        super(context, DATABASE_NAME, DATABASE_VERSION);
    }

    @Override
    public void onCreateNormalTables(SQLiteDatabase db) {
        db.execSQL(Table1Dao.DATABASE_CREATE_TABLE);
        db.execSQL(Table2Dao.DATABASE_CREATE_TABLE);
    }

    @Override
    public void onCreateLinkedTables(SQLiteDatabase db) {
        db.execSQL(Table3LinkedDao.DATABASE_CREATE_TABLE);
    }

    @Override
    public void onUpgradeNormalTables(SQLiteDatabase db) {
        db.execSQL(Table1Dao.DATABASE_UPDATE);
        db.execSQL(Table2Dao.DATABASE_UPDATE);
    }

    @Override
    public void onUpgradeLinkedTables(SQLiteDatabase db) {
        db.execSQL(Table3LinkedDao.DATABASE_UPDATE);
    }
}
