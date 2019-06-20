package cassandra.repository;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Statement;
import model.*;

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

    /**
     * Insert a new ApplicationSizeRating.
     *
     * @param applicationSizeRating New ApplicationSizeRating that will be inserted.
     */
    void insertApplicationSizeRating(ApplicationSizeRating applicationSizeRating);

    /**
     * Insert a new ApplicationAgeRating.
     *
     * @param applicationAgeRating New ApplicationAgeRating that will be inserted.
     */
    void insertApplicationAgeRating(ApplicationAgeRating applicationAgeRating);

    /**
     * Insert a new ApplicationGenreRating.
     *
     * @param applicationGenreRating New ApplicationGenreRating that will be inserted.
     */
    void insertApplicationGenreRating(ApplicationGenreRating applicationGenreRating);

    /**
     * Insert a new ApplicationDateRating.
     *
     * @param applicationDateRating New ApplicationDateRating that will be inserted.
     */
    void insertApplicationDateRating(ApplicationDateRating applicationDateRating);

    /**
     * Insert a new ApplicationVersionFeeling.
     *
     * @param applicationVersionFeeling New ApplicationVersionFeeling that will be inserted.
     */
    void insertApplicationVersionFeeling(ApplicationVersionFeeling applicationVersionFeeling );

    /**
     * Insert a new ApplicationCategoryRating.
     *
     * @param applicationCategoryRating New ApplicationCategoryRating that will be inserted.
     */
    void insertApplicationCategoryRating(ApplicationCategoryRating applicationCategoryRating );

    /**
     * Insert a new ApplicationCategory.
     *
     * @param applicationCategory New ApplicationCategory that will be inserted.
     */
    void insertApplicationCategory(ApplicationCategory applicationCategory );

    /**
     * Insert a new ApplicationFeelingRating.
     *
     * @param applicationFeelingRating New ApplicationFeelingRating that will be inserted.
     */
    void insertApplicationFeelingRating(ApplicationFeelingRating applicationFeelingRating );

}
