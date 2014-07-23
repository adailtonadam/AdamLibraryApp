package br.com.adam.adailton;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        Table1Dao dao1 = new Table1Dao(this.getContext());
        dao1.deleteAll();

        Table2Dao dao2 = new Table2Dao(this.getContext());
        dao2.deleteAll();

        Table3LinkedDao dao3 = new Table3LinkedDao(this.getContext());
        dao2.deleteAll();

        Table1 table1 = new Table1();
        table1.setName("item1");
        table1.setOtherField("otherfield1");
        table1.setFloatField(1F);
        table1.setLongField(1);
        long table1Id = dao1.insert(table1);

        Table2 table2 = new Table2();
        table2.setDescription("description1");
        long table2Id = dao2.insert(table2);

        Table3Linked table3 = new Table3Linked();
        table3.setTable1Id(table1Id);
        table3.setTable2Id(table2Id);
        long table3Id = dao3.insert(table3);




    }
}
