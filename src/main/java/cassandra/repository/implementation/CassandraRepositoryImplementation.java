package cassandra.repository.implementation;

import cassandra.repository.CassandraRepository;
import com.datastax.driver.core.*;
import model.ApplicationPriceRating;
import model.CategoryOneTotal;
import model.CategoryTwoTotal;
import model.Detail;

public class CassandraRepositoryImplementation implements CassandraRepository {

    private Session session;

    public CassandraRepositoryImplementation(Session session) {
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor) {
        String query =
                "CREATE KEYSPACE IF NOT EXISTS " +
                        keyspaceName + " WITH replication = {" +
                        "'class':'" + replicationStrategy +
                        "','replication_factor':" + replicationFactor +
                        "};";

        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    public void insertDetail(Detail detail) {
        String query = "INSERT INTO write_test.details " + "(id, cat_one, cat_two, value) " +
                "VALUES ('" + detail.getId() +
                "', " + detail.getCategoryOneId() + ", " + detail.getCategoryTwoId() + ", " + detail.getValue() + ");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    public void insertCategoryOneTotal(CategoryOneTotal categoryOneTotal) {
        String query = "INSERT INTO write_test.cat_one_sum (cat_one_id, total) " +
                "VALUES ( " + categoryOneTotal.getCategoryOneId() + "," + categoryOneTotal.getTotal() + ");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    public void insertCategoryTwoTotal(CategoryTwoTotal categoryTwoTotal) {
        String query = "INSERT INTO write_test.cat_two_sum (cat_one_id, total) " +
                "VALUES ( " + categoryTwoTotal.getCategoryTwoId() + "," + categoryTwoTotal.getTotal() + ");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    public void updateCategoryOneTotal(CategoryOneTotal categoryOneTotal) {
        String query = "UPDATE write_test.cat_one_sum_counter SET total = total + "
                + categoryOneTotal.getTotal() + " WHERE cat_one_id = " + categoryOneTotal.getCategoryOneId() + ";";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    public void updateCategoryTwoTotal(CategoryTwoTotal categoryTwoTotal) {
        try {
            String query = "UPDATE write_test.cat_one_sum_counter SET total = total + "
                    + categoryTwoTotal.getTotal() + " WHERE cat_one_id = " + categoryTwoTotal.getCategoryTwoId() + ";";
            session.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insertApplicationPriceRating(ApplicationPriceRating applicationPriceRating) {
        String query =
                "INSERT INTO original.application_price_rating " +
                        "(name, type, price, rating, installs, review_count) " +
                        "VALUES ('" + applicationPriceRating.getName() + "', '" + applicationPriceRating.getType()
                        + "', " + applicationPriceRating.getPrice() + ", " + applicationPriceRating.getRating() +
                        ", '" + applicationPriceRating.getInstalls() + "', " + applicationPriceRating.getReviewCount() + ");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statement getDetailAsStatement(Detail detail) {
        String query = "INSERT INTO write_test.details " + "(id, cat_one, cat_two, value) " +
                "VALUES ('" + detail.getId() +
                "', " + detail.getCategoryOneId() + ", " + detail.getCategoryTwoId() + ", " + detail.getValue() + ");";
        Statement statement = new SimpleStatement(query);
        return statement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeBatch(BatchStatement batchStatement) {
        this.session.execute(batchStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryOneTotal findCategoryOneById(Detail detail) {
        String query = "SELECT * FROM write_test.cat_one_sum WHERE cat_one_id = " + detail.getCategoryOneId() + ";";
        ResultSet rs = session.execute(query);
        CategoryOneTotal categoryOneTotal = new CategoryOneTotal();
        rs.forEach(r -> {
            categoryOneTotal.setCategoryOneId(r.getInt("cat_one_id"));
            categoryOneTotal.setTotal(r.getInt("total"));
        });
        return categoryOneTotal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryTwoTotal findCategoryTwoById(Detail detail) {
        String query = "SELECT * FROM write_test.cat_two_sum WHERE cat_two_id = " + detail.getCategoryTwoId() + ";";
        ResultSet rs = session.execute(query);
        CategoryTwoTotal categoryTwoTotal = new CategoryTwoTotal();
        rs.forEach(r -> {
            categoryTwoTotal.setCategoryTwoId(r.getInt("cat_two_id"));
            categoryTwoTotal.setTotal(r.getInt("total"));
        });
        return categoryTwoTotal;
    }
}
