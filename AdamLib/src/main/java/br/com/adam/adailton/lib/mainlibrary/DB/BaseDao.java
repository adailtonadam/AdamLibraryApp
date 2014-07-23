package br.com.adam.adailton.lib.mainlibrary.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDao {
    protected Context ctx;
    protected String tableName;
    protected String columnId;
    String[] columns;


    abstract public DB getNewDb(Context context);
    abstract public void getContentValues(ContentValues ctv, BaseTable table);
    abstract public BaseTable setTableValues(Cursor rs);



    public BaseDao(Context ctx, String tableName, String columnId, String[] columns) {
        this.ctx = ctx;
        this.tableName = tableName;
        this.columnId = columnId;
        this.columns = columns;
    }


    /*
     * -1 on error or tableid
     */
    public long insert(BaseTable table) {
        long retValue = -1;
        SQLiteDatabase db = getNewDb(ctx).getWritableDatabase();
        ContentValues ctv = new ContentValues();
        getContentValues(ctv, table);
        try {
            retValue = db.insert(tableName, null, ctv);
        } catch (Exception ex) {
            retValue = -1;
        }
        db.close();
        return retValue;

    }


    public void deleteAll() {
        SQLiteDatabase db = getNewDb(ctx).getWritableDatabase();
        db.delete(tableName, null, null);
        db.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='" + tableName + "';");
        db.close();
    }


    public boolean delete(BaseTable table) {
        SQLiteDatabase db = getNewDb(ctx).getWritableDatabase();
        boolean retValue = (db.delete(tableName, columnId + "=?", new String[]{"" + String.valueOf(table.getId())}) > 0);
        db.close();
        return retValue;
    }


    public boolean delete(long id) {
        SQLiteDatabase db = getNewDb(ctx).getWritableDatabase();
        boolean retValue = (db.delete(tableName, columnId + "=?", new String[]{"" + String.valueOf(id)}) > 0);
        db.close();
        return retValue;
    }

    public boolean update(BaseTable table) {
        SQLiteDatabase db = getNewDb(ctx).getWritableDatabase();
        ContentValues ctv = new ContentValues();
        getContentValues(ctv, table);
        int retValue = 0;
        try {
            retValue = db.update(tableName, ctv, columnId + "=?", new String[]{"" + String.valueOf(table.getId())});
        } catch (Exception ex) {
            retValue = 0;
        }
        if (retValue > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }


    public BaseTable getById(Long ID) {
        SQLiteDatabase db = getNewDb(ctx).getReadableDatabase();
        Cursor rs = db.query(tableName, columns, columnId + "=?", new String[]{ID.toString()}, null, null, null);

        BaseTable thing = null;

        if (rs.moveToFirst()) {
            thing = setTableValues(rs);
        }
        db.close();
        return thing;
    }

	
	
	
	/*	
	public List<Thing> getAll() {

		SQLiteDatabase db = getNewDb(ctx).getReadableDatabase();
		Cursor rs = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NOME , null);
		List<Thing> lista = new ArrayList<Thing>();
		while (rs.moveToNext()) {
			Thing vo = new Thing(rs.getLong(0), rs.getString(1), rs.getString(2), rs.getString(3));
			lista.add(vo);
		}
		db.close();
		return lista;
		
	}
	*/
	
	
	/*
	 public boolean insertList(List<Thing> vo) {
		int auxCount = 0;
		SQLiteDatabase db = getNewDb(ctx).getWritableDatabase();
		db.execSQL("BEGIN IMMEDIATE TRANSACTION");
		ContentValues ctv = new ContentValues();
		for (int i = 0; i < vo.size(); i++) {
			Thing auxVo = vo.get(i);
			ctv.put(COLUMN_NOME, auxVo.getNome());
			ctv.put(COLUMN_COR, auxVo.getCor());
			ctv.put(COLUMN_TIPO, auxVo.getTipo());
		
			db.insert(TABLE_NAME, null, ctv);
			auxCount++;
		}
		db.execSQL("COMMIT TRANSACTION");
		db.close();
		return auxCount == vo.size();
	}
	 */

	/*
	 	public Thing getByName(String  Nome) {
		SQLiteDatabase db = getNewDb(ctx).getReadableDatabase();
		String command = "SELECT * FROM " + TABLE_NAME + " WHERE "+COLUMN_NOME + "='" + Nome +"'";
		Cursor rs = db.rawQuery(command, null);
		Thing thing = null;

		if (rs.moveToFirst()) {
			thing = new Thing();
			thing.setId(rs.getInt(rs.getColumnIndex(COLUMN_ID)));
			thing.setNome(rs.getString(rs.getColumnIndex(COLUMN_NOME)));
			thing.setCor(rs.getString(rs.getColumnIndex(COLUMN_COR)));
			thing.setTipo(rs.getString(rs.getColumnIndex(COLUMN_TIPO)));
		}
		db.close();
		return thing;
	}
	 */


}
