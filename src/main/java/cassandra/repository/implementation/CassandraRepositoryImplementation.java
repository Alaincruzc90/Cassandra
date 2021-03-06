package cassandra.repository.implementation;

import cassandra.repository.CassandraRepository;
import com.datastax.driver.core.*;
import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import model.*;

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
    public void insertApplicationSizeRating(ApplicationSizeRating applicationSizeRating) {
        String query =
                "INSERT INTO original.application_size_rating " +
                        "(name, size, rating, installs, review_count) " +
                        "VALUES ('" + applicationSizeRating.getName() + "', " + applicationSizeRating.getSize()
                        + ", " + applicationSizeRating.getRating() + ", '" + applicationSizeRating.getInstalls() +
                        "', " + applicationSizeRating.getReviewCount() + ");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationAgeRating(ApplicationAgeRating applicationAgeRating) {
        String query =
                "INSERT INTO original.application_age_rating " +
                        "(age_group, rating, installs, review_count) " +
                        "VALUES ('" + applicationAgeRating.getAgeClasification()
                        + "', " + applicationAgeRating.getRating() + ", '" + applicationAgeRating.getInstalls() +
                        "', " + applicationAgeRating.getReviewCount() + ");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationGenreRating(ApplicationGenreRating applicationGenreRating) {
        String query =
                "INSERT INTO original.application_genre_rating " +
                        "(genre, rating, installs, review_count, category, name) " +
                        "VALUES ('" + applicationGenreRating.getGenre()
                        + "', " + applicationGenreRating.getRating() + ", '" + applicationGenreRating.getInstalls() +
                        "', " + applicationGenreRating.getReviewCount() + ", '"+applicationGenreRating.getCategory()
                        +"', '"+applicationGenreRating.getName()+"');";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationDateRating(ApplicationDateRating applicationDateRating) {
        String query =
                "INSERT INTO original.application_date_rating " +
                        "(name, last_update, rating, installs, review_count) " +
                        "VALUES ('" + applicationDateRating.getName() + "', '"+applicationDateRating.getLastUpdate()+"', "
                        + applicationDateRating.getRating() + ", '" + applicationDateRating.getInstalls() +
                        "', " + applicationDateRating.getReviewCount() + ");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationVersionFeeling(ApplicationVersionFeeling applicationVersionFeeling) {
        String query =
                "INSERT INTO original.application_version_feeling " +
                        "(name, os_version, rating, installs, review_count, feeling) " +
                        "VALUES ('" + applicationVersionFeeling.getName() + "', '"+applicationVersionFeeling.getAndroidVersion()+"', "
                        + applicationVersionFeeling.getRating() + ", '" + applicationVersionFeeling.getInstalls() +
                        "', " + applicationVersionFeeling.getReviewCount() + ", "+applicationVersionFeeling.getFeeling()+");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationCategoryRating(ApplicationCategoryRating applicationCategoryRating) {
        String query =
                "INSERT INTO original.application_category_rating " +
                        "(category, rating, installs, review_count) " +
                        "VALUES ('" + applicationCategoryRating.getCategory() + "', "
                        + applicationCategoryRating.getRating() + ", '" + applicationCategoryRating.getInstalls() +
                        "', " + applicationCategoryRating.getReviewCount() +");";
        session.execute(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationCategory(ApplicationCategory applicationCategory) {
        String query =
                "INSERT INTO original.application_category " +
                        "(category, name) " +
                        "VALUES ('" + applicationCategory.getCategory() + "', '" + applicationCategory.getName() +
                        "');";
        session.execute(query);
    }

    @Override
    public void insertApplicationFeelingRating(ApplicationFeelingRating applicationFeelingRating) {
        String query =
                "INSERT INTO original.application_feeling_rating " +
                        "(name, feeling, rating, review_count) " +
                        "VALUES ('" + applicationFeelingRating.getName() + "', '"+applicationFeelingRating.getFeeling()+"', "
                        + applicationFeelingRating.getRating() + ", " + applicationFeelingRating.getReviewCount() +");";
        session.execute(query);
    }

     @Override
     public void insertApplicationCategoryInstallation(ApplicationCategoryInstallation applicationCategoryInstallation) {
         String query1 =
                "INSERT INTO original.application_category_rating_one " +
                        "(category, name, installs) " +
                        "VALUES ('" + applicationCategoryInstallation.getCategory() + "', '" + applicationCategoryInstallation.getName() +
                        "', '"+applicationCategoryInstallation.getInstalls()+"');";

         String query2 =
                "INSERT INTO original.application_category_rating_two " +
                        "(category, name, installs) " +
                        "VALUES ('" + applicationCategoryInstallation.getCategory() + "', '" + applicationCategoryInstallation.getName() +
                        "', '"+applicationCategoryInstallation.getInstalls()+"');";

         String query3 =
                "INSERT INTO original.application_category_rating_three " +
                        "(category, name, installs) " +
                        "VALUES ('" + applicationCategoryInstallation.getCategory() + "', '" + applicationCategoryInstallation.getName() +
                        "', '"+applicationCategoryInstallation.getInstalls()+"');";
        session.execute(query1);
        session.execute(query2);
        session.execute(query3);
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
