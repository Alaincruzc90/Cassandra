package cassandra.repository;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Statement;
import model.ApplicationPriceRating;
import model.CategoryOneTotal;
import model.CategoryTwoTotal;
import model.Detail;

public interface CassandraRepository {

    /**
     * Create a new keyspace with a given name.
     *
     * @param keyspaceName        Name of the keyspace to be created.
     * @param replicationStrategy Replication strategy that we are going to use. Determines how
     *                            the replicas will be distributed.
     * @param replicationFactor   Replication factor. This means the number of replicas that our keyspace will have.
     */
    void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor);

    /**
     * Insert a new detail.
     *
     * @param detail Detail that will be inserted.
     */
    void insertDetail(Detail detail);

    /**
     * Given a detail, return it as a Cassandra statement.
     *
     * @param detail New detail that will be converted to an statement.
     * @return Cassandra statement.
     */
    Statement getDetailAsStatement(Detail detail);

    /**
     * Execute a Cassandra batch statement.
     *
     * @param batchStatement Batch statement that will be executed.
     */
    void executeBatch(BatchStatement batchStatement);

    /**
     * Insert a new category one detail total.
     *
     * @param categoryOneTotal Amount to be inserted or updated.
     */
    void insertCategoryOneTotal(CategoryOneTotal categoryOneTotal);

    /**
     * Insert a new category two detail total.
     *
     * @param categoryTwoTotal Amount to be inserted or updated.
     */
    void insertCategoryTwoTotal(CategoryTwoTotal categoryTwoTotal);

    /**
     * Update a category one detail total.
     *
     * @param categoryOneTotal Amount to be inserted or updated.
     */
    void updateCategoryOneTotal(CategoryOneTotal categoryOneTotal);

    /**
     * Update a category two detail total.
     *
     * @param categoryTwoTotal Amount to be inserted or updated.
     */
    void updateCategoryTwoTotal(CategoryTwoTotal categoryTwoTotal);

    /**
     * Insert a new ApplicationPriceRating.
     *
     * @param applicationPriceRating New ApplicationPriceRating that will be inserted.
     */
    void insertApplicationPriceRating(ApplicationPriceRating applicationPriceRating);

}
