package br.com.adam.adailton.adamlibraryapp;

import br.com.adam.adailton.lib.mainlibrary.DB.BaseTable;

/**
 * Created by ad036950 on 23/07/2014.
 */
public class Table2  extends BaseTable {

    private String description;


    public Table2() {
        super();
        description = "";
    }

    public Table2(long id, String titulo) {
        super(id);
        this.description = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
