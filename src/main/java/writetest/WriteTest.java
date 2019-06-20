package writetest;

import cassandra.service.implementation.CassandraServiceImplementation;
import com.datastax.driver.core.BatchStatement;
import model.Detail;
import util.IdRandomize;

import java.util.ArrayList;
import java.util.Calendar;

public class WriteTest {

    private int maxThreads;
    private CassandraServiceImplementation cassandraServiceImplementation;

    public WriteTest(int maxThreads) {
        this.cassandraServiceImplementation = new CassandraServiceImplementation();
        this.maxThreads = maxThreads;
    }

    public void writeOnlyDetails(int rows, boolean doBatch) {

        // Save the references to all our threads.
        ArrayList<Thread> threads = new ArrayList<>();

        // Get the actual time.
        long start = System.currentTimeMillis();
        System.out.println("Actual time: " + Calendar.getInstance().getTime());

        // Get the number of rows that each thread will need to insert.
        int maxRows = (int) (rows / (double) this.maxThreads);

        for (int j = 0; j < maxThreads; j++) {

            // Set each thread process and add it to our thread list.
            Thread t = new Thread(() -> {
                if (doBatch) {
                    threadInsertDetailInBatch(maxRows);
                } else {
                    threadInsertDetail(maxRows);
                }
            });
            threads.add(t);
            t.start();
        }

        // Wait for all threads to finish.
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Get the time when the process finished.
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds to insert " + rows + " rows.");

        cassandraServiceImplementation.closeConnection();
    }

    /**
     * Method that will be executed by each thread to insert a new detail.
     *
     * @param rows Max number on rows that will be inserted.
     */
    private void threadInsertDetail(int rows) {

        // Each thread must insert a given number of rows.
        for (int i = 0; i < rows; i++) {

            // Create the detail.
            String id = IdRandomize.getRandomId(15);
            int categoryOne = (int) (Math.random() * 20);
            int categoryTwo = (int) (Math.random() * 100);
            int value = (int) (Math.random() * 35000);
            Detail detail = new Detail(id, categoryOne, categoryTwo, value);

            // Insert the new data.
            cassandraServiceImplementation.insertDetail(detail);
        }

    }

    /**
     * Method that will be executed by each thread to insert a new detail.
     *
     * @param rows Max number on rows that will be inserted.
     */
    private void threadInsertDetailInBatch(int rows) {

        // Create the new batch which will be executed.
        BatchStatement batchStatement = new BatchStatement();

        // Each thread must insert a given number of rows.
        for (int i = 0; i < rows; i++) {

            // Create the detail.
            Detail detail = this.createNewDetail();

            // Insert the new statement from the new detail.
            batchStatement.add(cassandraServiceImplementation.getDetailAsStatement(detail));
        }

        // Execute the batch.
        cassandraServiceImplementation.executeBatch(batchStatement);

    }

    /**
     * Create a new random detail.
     *
     * @return New random detail.
     */
    private Detail createNewDetail() {
        String id = IdRandomize.getRandomId(15);
        int categoryOne = (int) (Math.random() * 20);
        int categoryTwo = (int) (Math.random() * 100);
        int value = (int) (Math.random() * 35000);
        return new Detail(id, categoryOne, categoryTwo, value);
    }

}
