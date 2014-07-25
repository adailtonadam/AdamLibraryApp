package br.com.adam.adailton.adamlibraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.adam.adailton.lib.mainlibrary.DB.BaseDao;
import br.com.adam.adailton.lib.mainlibrary.DB.BaseTable;
import br.com.adam.adailton.lib.mainlibrary.DB.DB;

/**
 * Created by ad036950 on 23/07/2014.
 */
public class Table3LinkedDao  extends BaseDao {

    public static String TABLE_NAME = "table3";

    public static final String DATABASE_UPDATE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TABLE_1_ID = "table1_id";
    public static final String COLUMN_TABLE_2_ID = "table2_id";

    public static String[] COLUMNS = { COLUMN_ID, COLUMN_TABLE_1_ID, COLUMN_TABLE_2_ID };

    public static final String DATABASE_CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            +      COLUMN_ID + " integer primary key  autoincrement "
            + "," + COLUMN_TABLE_1_ID + " integer not null"
            + "," + COLUMN_TABLE_2_ID + " integer not null"
            + ");";





    public Table3LinkedDao(Context ctx) {
        super(ctx, TABLE_NAME, COLUMN_ID, COLUMNS);
    }

    @Override
    public DB getNewDb(Context context) {
        return new TestDB(context);
    }

    @Override
    public void getContentValues(ContentValues ctv, BaseTable table) {
        Table3Linked item = (Table3Linked) table;
        ctv.put(COLUMN_TABLE_1_ID, item.getTable1Id());
        ctv.put(COLUMN_TABLE_2_ID, item.getTable2Id());
    }

    @Override
    public BaseTable setTableValues(Cursor rs) {
        Table3Linked item = new Table3Linked();
        item.setId(rs.getLong(rs.getColumnIndex(COLUMN_ID)));
        item.setTable1Id(rs.getLong(rs.getColumnIndex(COLUMN_TABLE_1_ID)));
        item.setTable2Id(rs.getLong(rs.getColumnIndex(COLUMN_TABLE_2_ID)));
        return item;
    }





    public List<Table3Linked> getAll() {
        SQLiteDatabase db = getNewDb(ctx).getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + TABLE_NAME  , null);
        List<Table3Linked> newList = new ArrayList<Table3Linked>();
        while (rs.moveToNext()) {
            newList.add((Table3Linked)setTableValues(rs));
        }
        db.close();
        return newList;
    }




}
