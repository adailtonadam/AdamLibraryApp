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


public class Table2Dao  extends BaseDao {

    public static String TABLE_NAME = "table2";

    public static final String DATABASE_UPDATE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "description";

    public static String[] COLUMNS = { COLUMN_ID, COLUMN_DESCRIPTION };

    public static final String DATABASE_CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            +      COLUMN_ID + " integer primary key  autoincrement "
            + "," + COLUMN_DESCRIPTION + " text not null unique"
             + ");";





    public Table2Dao(Context ctx) {
        super(ctx, TABLE_NAME, COLUMN_ID, COLUMNS);
    }

    @Override
    public DB getNewDb(Context context) {
        return new TestDB(context);
    }

    @Override
    public void getContentValues(ContentValues ctv, BaseTable table) {
        Table2 item = (Table2) table;
        ctv.put(COLUMN_DESCRIPTION, item.getDescription());
    }

    @Override
    public BaseTable setTableValues(Cursor rs) {
        Table2 item = new Table2();
        item.setId(rs.getLong(rs.getColumnIndex(COLUMN_ID)));
        item.setDescription(rs.getString(rs.getColumnIndex(COLUMN_DESCRIPTION)));
        return item;
    }

    public List<Table2> getAllOrderBy() {
        SQLiteDatabase db = getNewDb(ctx).getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_DESCRIPTION , null);
        List<Table2> newList = new ArrayList<Table2>();
        while (rs.moveToNext()) {
            newList.add((Table2)setTableValues(rs));
        }
        db.close();
        return newList;

    }

    public List<Table2> getAll() {
        SQLiteDatabase db = getNewDb(ctx).getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + TABLE_NAME  , null);
        List<Table2> newList = new ArrayList<Table2>();
        while (rs.moveToNext()) {
            newList.add((Table2)setTableValues(rs));
        }
        db.close();
        return newList;
    }
}
