package br.com.adam.adailton;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.com.adam.adailton.lib.mainlibrary.DB.BaseDao;
import br.com.adam.adailton.lib.mainlibrary.DB.BaseTable;
import br.com.adam.adailton.lib.mainlibrary.DB.DB;

/**
 * Created by ad036950 on 23/07/2014.
 */
public class Table1Dao extends BaseDao {

    public static String TABLE_NAME = "table1";

    public static final String DATABASE_UPDATE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_OTHER_FIELD = "other_field";
    public static final String COLUMN_INT_FIELD = "int_field";
    public static final String COLUMN_REAL_FIELD = "real_field";

    public static String[] COLUMNS = { COLUMN_ID, COLUMN_NAME,COLUMN_OTHER_FIELD,COLUMN_INT_FIELD,COLUMN_REAL_FIELD};

    public static final String DATABASE_CREATE_TABLE = "create table " + TABLE_NAME
            + "("
            +      COLUMN_ID + " integer primary key  autoincrement "
            + "," + COLUMN_NAME + " text not null unique"
            + "," + COLUMN_OTHER_FIELD + " text not null"
            + "," + COLUMN_INT_FIELD + " integer"
            + "," + COLUMN_REAL_FIELD + " real "
            + ");";





    public Table1Dao(Context ctx) {
        super(ctx, TABLE_NAME, COLUMN_ID, COLUMNS);
    }

    @Override
    public DB getNewDb(Context context) {
        return new TestDB(context);
    }

    @Override
    public void getContentValues(ContentValues ctv, BaseTable table) {
        Table1 item = (Table1) table;
        ctv.put(COLUMN_NAME, item.getName());
        ctv.put(COLUMN_OTHER_FIELD, item.getOtherField());
        ctv.put(COLUMN_INT_FIELD, item.getLongField());
        ctv.put(COLUMN_REAL_FIELD, item.getFloatField());
    }

    @Override
    public BaseTable setTableValues(Cursor rs) {
        Table1 item = new Table1();
        item.setId(rs.getLong(rs.getColumnIndex(COLUMN_ID)));
        item.setName(rs.getString(rs.getColumnIndex(COLUMN_NAME)));
        item.setOtherField(rs.getString(rs.getColumnIndex(COLUMN_OTHER_FIELD)));
        item.setLongField(rs.getLong(rs.getColumnIndex(COLUMN_INT_FIELD)));
        item.setFloatField(rs.getFloat(rs.getColumnIndex(COLUMN_REAL_FIELD)));
        return item;
    }
}
