package br.com.adam.adailton.lib.mainlibrary.DB;


public class BaseTable {
	
	private long id;

    public BaseTable(){
        id = -1;
    }

    public BaseTable(long id){
        this.id = id;
    }
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
