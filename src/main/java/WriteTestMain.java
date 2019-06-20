import writetest.WriteTest;

public class WriteTestMain {

    public static void main(String[] args) {

        WriteTest writeTest = new WriteTest(1000);
        try {
            writeTest.writeOnlyDetails(50000, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
