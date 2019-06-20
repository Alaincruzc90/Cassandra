import dataprocessing.DataProcessing;

public class Main {

    public static void main(String[] args) {

        DataProcessing dataProcessing = new DataProcessing();
        try {
            dataProcessing.insertData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
