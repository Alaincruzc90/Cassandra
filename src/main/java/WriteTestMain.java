import org.apache.commons.lang3.math.NumberUtils;
import writetest.WriteTest;

public class WriteTestMain {

    public static void main(String[] args) {

        // Number of threads desired by the user.
        int numberOfThreads = 100;
        // Number of rows that will be inserted.
        int numberOfRows = 50000;
        // Test we are running.
        int test = 1;

        // Check that we have 3 parameters, else we don't change the defaults parameters.
        if (args.length == 3) {
            numberOfThreads = NumberUtils.toInt(args[0], numberOfThreads);
            numberOfRows = NumberUtils.toInt(args[1], numberOfRows);
            test = NumberUtils.toInt(args[2], test);
        }

        // Create the write test object, where we have all our tests.
        WriteTest writeTest = new WriteTest(numberOfThreads);

        // Check the type of test we want to run.
        switch (test) {
            case 1:
                System.out.println("PRUEBA #1: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura unicamente en una tabla sin lecturas.");
                writeTest.writeOnlyDetails(numberOfRows, false);
                break;
            default:
                System.out.println("PRUEBA #1: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura unicamente en una tabla sin lecturas.");
                writeTest.writeOnlyDetails(numberOfRows, false);
                break;
        }

    }
}
