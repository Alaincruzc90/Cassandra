import org.apache.commons.lang3.math.NumberUtils;
import writetest.WriteTest;

import java.util.Scanner;

public class WriteTestMain {

    //This integer represents the default value of the caches options. If this value is chosen by the user the default value of that element in the cache is used.
    private static int defaultValue = 100;

    // Method that return an user selected integer, it makes sure that the input is actually an integer.
    // It uses a primary message to tell the user what the input is about. Also receives a second message to tell the user what happened if the input was not an integer.
    // *In this case an empty string is considered the default value(defined in this class).
    private static int receiveIntegerFromUser(String PrimaryMessage, String errorMessage, boolean isTest) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(PrimaryMessage);
        while (true) {
            String userInput = scanner.nextLine();
            try {
                int value = Integer.parseInt(userInput);
                if (isTest) {
                    if (value >= 1 && value < 6) {
                        return value;
                    } else {
                        System.out.println(errorMessage);
                    }
                } else {
                    return value;
                }
            } catch (Exception e) {
                if (e.getClass().getCanonicalName().equals("java.lang.NumberFormatException")) {
                    if (userInput.isEmpty()) {
                        return defaultValue;
                    }
                    System.out.println(errorMessage);
                } else
                    e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        // Number of threads desired by the user.
        int numberOfThreads = receiveIntegerFromUser("Cantidad de hilos que desea usar en la simulación.", "Por favor, digite un número entero.", false);
        // Number of rows that will be inserted.
        int numberOfRows = receiveIntegerFromUser("Cantidad de filas que desea ingresar en la simulación.", "Por favor, digite un número entero.", false);

        // Show the available tests.
        System.out.println("Hilos: " + numberOfThreads + " | Filas: " + numberOfRows);
        System.out.println("");
        System.out.println("*************");
        System.out.println("-- PRUEBAS --");
        System.out.println("*************");
        System.out.println("");
        System.out.println("A continuación, le mostramos diferentes tipos de pruebas que puede realizar.");
        System.out.println("Cada prueba tiene un número asociado que puede digitar para correr dicha prueba.");
        System.out.println("");
        System.out.println("");
        System.out.println("PRUEBA #1: Descripción: Escritura unicamente en una tabla sin lecturas.");
        System.out.println("Digite 1.");
        System.out.println("");
        System.out.println("PRUEBA #2: Descripción: Escritura en dos tablas, con un tercio de lecturas.");
        System.out.println("Digite 2.");
        System.out.println("");
        System.out.println("PRUEBA #3: Descripción: Escritura en dos tablas, con lecturas en todas las escrituras.");
        System.out.println("Digite 3.");
        System.out.println("");
        System.out.println("PRUEBA #4: Descripción: Escritura en dos tablas, con lecturas en todas las escrituras, pero usando 'update counter'.");
        System.out.println("Digite 4.");
        System.out.println("");
        System.out.println("PRUEBA #5: Descripción: Escritura en tres tablas, con un tercio de lecturas. Digite 5.");
        System.out.println("Digite 5.");
        System.out.println("");
        System.out.println("Si no desea continuar con ninguna de las pruebas, puede digitar 0 para salir.");

        // Test we are running.
        int test = receiveIntegerFromUser("¿Cual prueba es la que desea correr?", "Por favor, digite un número entero entre el 0 y el 5.", true);

        // Create the write test object, where we have all our tests.
        WriteTest writeTest = new WriteTest(numberOfThreads);

        // If the user chose 0, then we simply just skip the test.
        if (test == 0) return;

        // Check the type of test we want to run.
        System.out.println("");
        System.out.println("Vamos a correr la siguiente prueba.");
        switch (test) {
            case 1:
                System.out.println("PRUEBA #1: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura unicamente en una tabla sin lecturas.");
                writeTest.writeOnlyDetails(numberOfRows, false);
                break;
            case 2:
                System.out.println("PRUEBA #2: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura en dos tablas, con un tercio de lecturas.");
                writeTest.writeDetailsWithCategoryOneTotal(numberOfRows);
                break;
            case 3:
                System.out.println("PRUEBA #3: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura en dos tablas, con lecturas en todas las escrituras.");
                writeTest.writeDetailsWithCategoryOneTotalAlwaysRead(numberOfRows);
                break;
            case 4:
                System.out.println("PRUEBA #4: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura en dos tablas, con lecturas en todas las escrituras, pero usando 'update counter'.");
                writeTest.writeDetailsWithCategoryOneTotalCounter(numberOfRows);
                break;
            case 5:
                System.out.println("PRUEBA #5: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura en tres tablas, con un tercio de lecturas.");
                writeTest.writeDetailsWithCategories(numberOfRows);
                break;
            default:
                System.out.println("PRUEBA #1: " + numberOfThreads + " hilos, " + numberOfRows + " filas.");
                System.out.println("-- Descripción: Escritura unicamente en una tabla sin lecturas.");
                writeTest.writeOnlyDetails(numberOfRows, false);
                break;
        }

    }
}
