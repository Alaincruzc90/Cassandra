import dataprocessing.DataProcessing;
import writetest.WriteTest;

public class Main {

    public static void main(String[] args) {

//        DataProcessing dataProcessing = new DataProcessing();
//        try {
//            dataProcessing.insertData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        WriteTest writeTest = new WriteTest(1000);
        try {
            writeTest.writeOnlyDetails(50000, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
