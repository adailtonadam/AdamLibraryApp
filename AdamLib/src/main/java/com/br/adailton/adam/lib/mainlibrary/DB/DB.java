package com.br.adailton.adam.lib.mainlibrary.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

abstract class DB extends SQLiteOpenHelper {



    /*
    * Executed to create new tables not linked
    * Ex.:db.execSQL(BaseDao.DATABASE_CREATE_TABLE);
    * */
    abstract void onCreateNormalTables(SQLiteDatabase db);

    /*
    * Executed to create new linked tables
    * Ex.:db.execSQL(BaseDao.DATABASE_CREATE_TABLE);
    * */
    abstract void onCreateLinkedTables(SQLiteDatabase db);

    /*
    * Executed to upgrade  tables not linked
    * Ex.:db.execSQL(BaseDao.DATABASE_UPDATE);
    * */
    abstract void onUpgradeNormalTables(SQLiteDatabase db);

     /*
    * Executed to upgrade new linked tables
    * Ex.:db.execSQL(BaseDao.DATABASE_UPDATE);
    * */
    abstract void onUpgradeLinkedTables(SQLiteDatabase db);


    /*
    * DATABASE_NAME : Ex.: "mydb.db"
    * DATABASE_VERSION : Number > 0
    * */
	public DB(Context context, String DATABASE_NAME, int DATABASE_VERSION) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    onCreateNormalTables(db);
        onCreateLinkedTables(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DB.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		onUpgradeLinkedTables(db);
        onUpgradeNormalTables(db);

		onCreate(db);
	}


}