package cassandra.service;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Statement;
import model.*;

public interface CassandraService {

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
    void insertApplicationVersionFeeling(ApplicationVersionFeeling applicationVersionFeeling);

    /**
     * Insert a new ApplicationCategoryRating.
     *
     * @param applicationCategoryRating New ApplicationCategoryRating that will be inserted.
     */
    void insertApplicationCategoryRating(ApplicationCategoryRating applicationCategoryRating);

    /**
     * Insert a new ApplicationCategory.
     *
     * @param applicationCategory New ApplicationCategory that will be inserted.
     */
    void insertApplicationCategory(ApplicationCategory applicationCategory);

    /**
     * Insert a new ApplicationFeelingRating.
     *
     * @param applicationFeelingRating New ApplicationFeelingRating that will be inserted.
     */
    void insertApplicationFeelingRating(ApplicationFeelingRating applicationFeelingRating);

    /**
     * Close our connection with Cassandra.
     */
    void closeConnection();

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

}
