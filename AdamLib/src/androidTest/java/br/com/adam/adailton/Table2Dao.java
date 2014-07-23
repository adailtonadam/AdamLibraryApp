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
public class Table2Dao  extends BaseDao {

    public static String TABLE_NAME = "table1";

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
}
