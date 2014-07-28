package br.com.adam.adailton.adamlibraryapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void testDb() {
        Table1Dao dao1 = new Table1Dao(this.getApplicationContext());
        dao1.deleteAll();

        Table2Dao dao2 = new Table2Dao(this.getApplicationContext());
        dao2.deleteAll();

        Table3LinkedDao dao3 = new Table3LinkedDao(this.getApplicationContext());
        dao3.deleteAll();

        Table1 table1 = new Table1();
        table1.setName("item1");
        table1.setOtherField("otherfield1");
        table1.setFloatField(1F);
        table1.setLongField(1);
        long table1Id = dao1.insert(table1);

        if (table1Id != 1) {
            Toast.makeText(this, "teste nok table1 insert", Toast.LENGTH_LONG).show();
            return;
        }

        Table2 table2 = new Table2();
        table2.setDescription("description1");
        long table2Id = dao2.insert(table2);

        if (table2Id != 1) {
            Toast.makeText(this, "teste nok table2 insert", Toast.LENGTH_LONG).show();
            return;
        }

        Table3Linked table3 = new Table3Linked();
        table3.setTable1Id(table1Id);
        table3.setTable2Id(table2Id);
        long table3Id = dao3.insert(table3);
        if (table3Id != 1) {
            Toast.makeText(this, "teste nok table3 insert", Toast.LENGTH_LONG).show();
            return;
        }

        List<Table1> listTable1 = dao1.getAllOrderBy();
        List<Table2> listTable2 = dao2.getAllOrderBy();
        List<Table3Linked> listTable3 = dao3.getAll();
        Log.d("Debug", listTable1.get(0).getId() + ": " +
                listTable1.get(0).getName() + ":" +
                listTable1.get(0).getOtherField() + ": " +
                listTable1.get(0).getLongField() + ": " +
                listTable1.get(0).getFloatField());

        Log.d("Debug", listTable2.get(0).getId() + ": " +
                listTable2.get(0).getDescription());

        Log.d("Debug", listTable3.get(0).getId() + ": " +
                listTable3.get(0).getTable1Id() + ": " +
                listTable3.get(0).getTable2Id());

        Toast.makeText(this, "teste ok", Toast.LENGTH_LONG).show();
    }


    public void onButtonDbTestClick(View v) {
        testDb();
    }
}


