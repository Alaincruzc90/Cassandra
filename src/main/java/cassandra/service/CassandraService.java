package cassandra.service;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Statement;
import model.ApplicationPriceRating;
import model.CategoryOneTotal;
import model.CategoryTwoTotal;
import model.Detail;

public interface CassandraService {

    /**
     * Insert a new ApplicationPriceRating.
     *
     * @param applicationPriceRating New ApplicationPriceRating that will be inserted.
     */
    void insertApplicationPriceRating(ApplicationPriceRating applicationPriceRating);

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
     * Given an Detail find it's current category one total.
     *
     * @param detail Detail from which we want to read it's category one total.
     * @return CategoryOneTotal.
     */
    CategoryOneTotal findCategoryOneById(Detail detail);

    /**
     * Given an Detail find it's current category one total.
     *
     * @param detail Detail from which we want to read it's category one total.
     * @return CategoryOneTotal.
     */
    CategoryTwoTotal findCategoryTwoById(Detail detail);

}
