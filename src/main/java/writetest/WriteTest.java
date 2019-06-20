package writetest;

import cassandra.service.implementation.CassandraServiceImplementation;
import com.datastax.driver.core.BatchStatement;
import model.CategoryOneTotal;
import model.CategoryTwoTotal;
import model.Detail;
import util.IdRandomize;

import java.util.ArrayList;
import java.util.Calendar;

public class WriteTest {

    private int maxThreads;
    private int totalRows = 0;
    private int readCount = 0;
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
     * Insert a new detail and one third of the time read and update a total
     * from a category one table.
     *
     * @param rows Max number of rows that will be inserted.
     */
    public void writeDetailsWithCategoryOneTotal(int rows) {

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
                threadInsertWithCategoryOneTotal(maxRows);
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
        System.out.println(sec + " seconds to insert " + totalRows + " rows.");
        System.out.println(readCount + " reads.");

        cassandraServiceImplementation.closeConnection();

    }

    /**
     * Insert a new detail and always read and update a total
     * from a category one table.
     *
     * @param rows Max number of rows that will be inserted.
     */
    public void writeDetailsWithCategoryOneTotalAlwaysRead(int rows) {

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
                threadInsertWithCategoryOneTotalAlwaysRead(maxRows);
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
        System.out.println(sec + " seconds to insert " + totalRows + " rows.");
        System.out.println(readCount + " reads.");

        cassandraServiceImplementation.closeConnection();

    }

    /**
     * Insert a new detail and one third of the time read and update a total
     * from a category one table.
     *
     * @param rows Max number of rows that will be inserted.
     */
    public void writeDetailsWithCategoryOneTotalCounter(int rows) {

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
                threadInsertWithCategoryOneTotalCounter(maxRows);
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
        System.out.println(sec + " seconds to insert " + totalRows + " rows.");
        System.out.println(readCount + " reads.");

        cassandraServiceImplementation.closeConnection();

    }

    /**
     * Insert a new detail and one third of the time reads and updates a total
     * from the categories table.
     *
     * @param rows Max number of rows that will be inserted.
     */
    public void writeDetailsWithCategories(int rows) {

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
                threadInsertWithCategories(maxRows);
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
        System.out.println(sec + " seconds to insert " + totalRows + " rows.");
        System.out.println(readCount + " reads.");

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
            Detail detail = this.createNewDetail(15);

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
            Detail detail = this.createNewDetail(15);

            // Insert the new statement from the new detail.
            batchStatement.add(cassandraServiceImplementation.getDetailAsStatement(detail));
        }

        // Execute the batch.
        cassandraServiceImplementation.executeBatch(batchStatement);

    }

    /**
     * Thread process, in which it inserts a new detail and one third of the time reads and updates a total
     * from a category one table.
     *
     * @param rows Rows to be inserted per thread.
     */
    private void threadInsertWithCategoryOneTotal(int rows) {

        // Each thread must insert a given number of rows.
        for (int i = 0; i < rows; i++) {

            // Create the detail.
            Detail detail = this.createNewDetail(15);

            // Insert the new data.
            cassandraServiceImplementation.insertDetail(detail);
            this.addTotalRow(1);

            // Read and update one third of the time.
            int random = (int) (Math.random() * 3 + 1);
            if (random == 1) {

                CategoryOneTotal categoryOneTotal = cassandraServiceImplementation.findCategoryOneById(detail);
                addRead(1);

                // If the category doesn't exist, then we need to add the new values.
                if(categoryOneTotal.getTotal() == null) {
                    categoryOneTotal.setCategoryOneId(detail.getCategoryOneId());
                    categoryOneTotal.setTotal(detail.getValue());
                }

                // Modify the category one and update.
                categoryOneTotal.setTotal(categoryOneTotal.getTotal()+detail.getValue());
                cassandraServiceImplementation.insertCategoryOneTotal(categoryOneTotal);
                this.addTotalRow(1);

            }

        }

    }

    /**
     * Thread process, in which it inserts a new detail and one third of the time reads and updates a total
     * from the categories table.
     *
     * @param rows Rows to be inserted per thread.
     */
    private void threadInsertWithCategories(int rows) {

        // Each thread must insert a given number of rows.
        for (int i = 0; i < rows; i++) {

            // Create the detail.
            Detail detail = this.createNewDetail(15);

            // Insert the new data.
            cassandraServiceImplementation.insertDetail(detail);
            this.addTotalRow(1);

            // Read and update one third of the time.
            int random = (int) (Math.random() * 3 + 1);
            if (random == 1) {

                CategoryOneTotal categoryOneTotal = cassandraServiceImplementation.findCategoryOneById(detail);
                CategoryTwoTotal categoryTwoTotal = cassandraServiceImplementation.findCategoryTwoById(detail);
                addRead(2);

                // If a category doesn't exist, then we need to add the new values.
                if(categoryOneTotal.getTotal() == null) {
                    categoryOneTotal.setCategoryOneId(detail.getCategoryOneId());
                    categoryOneTotal.setTotal(detail.getValue());
                }
                if(categoryTwoTotal.getTotal() == null) {
                    categoryTwoTotal.setCategoryTwoId(detail.getCategoryTwoId());
                    categoryTwoTotal.setTotal(detail.getValue());
                }

                // Modify the category one and update.
                categoryOneTotal.setTotal(categoryOneTotal.getTotal()+detail.getValue());
                categoryTwoTotal.setTotal(categoryTwoTotal.getTotal()+detail.getValue());
                cassandraServiceImplementation.insertCategoryOneTotal(categoryOneTotal);
                this.addTotalRow(2);

            }

        }

    }

    /**
     * Thread process, in which it inserts a new detail and always reads and updates a total
     * from a category one table.
     *
     * @param rows Rows to be inserted per thread.
     */
    private void threadInsertWithCategoryOneTotalAlwaysRead(int rows) {

        // Each thread must insert a given number of rows.
        for (int i = 0; i < rows; i++) {

            // Create the detail.
            Detail detail = this.createNewDetail(15);

            // Insert the new data.
            cassandraServiceImplementation.insertDetail(detail);

            // Read and update one third of the time.
            CategoryOneTotal categoryOneTotal = cassandraServiceImplementation.findCategoryOneById(detail);
            addRead(1);

            // If the category doesn't exist, then we need to add the new values.
            if(categoryOneTotal.getTotal() == null) {
                categoryOneTotal.setCategoryOneId(detail.getCategoryOneId());
                categoryOneTotal.setTotal(detail.getValue());
            }

            // Modify the category one and update.
            categoryOneTotal.setTotal(categoryOneTotal.getCategoryOneId()+detail.getValue());
            cassandraServiceImplementation.insertCategoryOneTotal(categoryOneTotal);
            this.addTotalRow(2);

        }

    }

    /**
     * Thread process, in which it inserts a new detail and always reads and updates a total
     * from a category one table.
     *
     * @param rows Rows to be inserted per thread.
     */
    private void threadInsertWithCategoryOneTotalCounter(int rows) {

        // Each thread must insert a given number of rows.
        for (int i = 0; i < rows; i++) {

            // Create the detail.
            Detail detail = this.createNewDetail(15);

            // Insert the new data.
            cassandraServiceImplementation.insertDetail(detail);

            // Update the category one corresponding to the detail.
            CategoryOneTotal categoryOneTotal = new CategoryOneTotal(detail.getCategoryOneId(), detail.getValue());
            cassandraServiceImplementation.updateCategoryOneTotal(categoryOneTotal);
            this.addTotalRow(2);

        }

    }

    /**
     * Create a new random detail.
     *
     * @return New random detail.
     */
    private Detail createNewDetail(int length) {
        String id = IdRandomize.getRandomId(length);
        int categoryOne = (int) (Math.random() * 20);
        int categoryTwo = (int) (Math.random() * 100);
        int value = (int) (Math.random() * 35000);
        return new Detail(id, categoryOne, categoryTwo, value);
    }

    /**
     * Increase the read count using an atomic method.
     */
    private synchronized void addRead(int amount) {
        readCount += amount;
    }

    private synchronized void addTotalRow(int amount) {
        totalRows += amount;
    }

}
