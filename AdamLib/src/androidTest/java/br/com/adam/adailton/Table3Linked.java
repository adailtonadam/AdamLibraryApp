package br.com.adam.adailton;

import br.com.adam.adailton.lib.mainlibrary.DB.BaseTable;

/**
 * Created by ad036950 on 23/07/2014.
 */
public class Table3Linked extends BaseTable {

    private long table1Id;
    private long table2Id;

    public long getTable1Id() {
        return table1Id;
    }

    public void setTable1Id(long table1Id) {
        this.table1Id = table1Id;
    }

    public long getTable2Id() {
        return table2Id;
    }

    public void setTable2Id(long table2Id) {
        this.table2Id = table2Id;
    }

    public Table3Linked() {
        super();
    }

    public Table3Linked(long id, long table1Id, long table2Id) {
        super(id);
        this.table1Id = table1Id;
        this.table2Id = table2Id;
    }
}