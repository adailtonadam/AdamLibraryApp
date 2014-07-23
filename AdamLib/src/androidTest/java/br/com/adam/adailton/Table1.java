package br.com.adam.adailton;

import br.com.adam.adailton.lib.mainlibrary.DB.BaseTable;

/**
 * Created by ad036950 on 23/07/2014.
 */
public class Table1  extends BaseTable{

    private String name;
    private String otherField;
    private long   longField;
    private float floatField;

    public long getLongField() {
        return longField;
    }

    public void setLongField(long longField) {
        this.longField = longField;
    }

    public float getFloatField() {
        return floatField;
    }

    public void setFloatField(float floatField) {
        this.floatField = floatField;
    }

    public Table1(){
        super();
        name = "";
    }
    public Table1(long id, String nome) {
        super(id);
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherField() {
        return otherField;
    }

    public void setOtherField(String otherField) {
        this.otherField = otherField;
    }
}
