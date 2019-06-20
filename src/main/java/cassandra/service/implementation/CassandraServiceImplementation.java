package cassandra.service.implementation;

import cassandra.connector.CassandraConnector;
import cassandra.repository.implementation.CassandraRepositoryImplementation;
import cassandra.service.CassandraService;
import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Statement;
import model.*;

import java.util.ResourceBundle;

public class CassandraServiceImplementation implements CassandraService {

    private CassandraRepositoryImplementation cassandraRepository;
    private CassandraConnector cassandraConnector;

    public CassandraServiceImplementation() {

        // First, read the required authentication configuration from the properties file.
        ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.cassandra");
        String host = resourceBundle.getString("cassandra.host");
        String username = resourceBundle.getString("cassandra.username");
        String password = resourceBundle.getString("cassandra.password");

        // Now, let's create a connection with Cassandra.
        this.cassandraConnector = new CassandraConnector();
        // Since we are using Cassandra's default port, we can leave it as null.
        this.cassandraConnector.connect(host, null, username, password);

        // Get the current session and build our repository.
        this.cassandraRepository = new CassandraRepositoryImplementation(this.cassandraConnector.getSession());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertDetail(Detail detail) {
        this.cassandraRepository.insertDetail(detail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statement getDetailAsStatement(Detail detail) {
        return this.cassandraRepository.getDetailAsStatement(detail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeBatch(BatchStatement batchStatement) {
        this.cassandraRepository.executeBatch(batchStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationPriceRating(ApplicationPriceRating applicationPriceRating) {
        this.cassandraRepository.insertApplicationPriceRating(applicationPriceRating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationSizeRating(ApplicationSizeRating applicationSizeRating) {
        this.cassandraRepository.insertApplicationSizeRating(applicationSizeRating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationAgeRating(ApplicationAgeRating applicationAgeRating) {
        this.cassandraRepository.insertApplicationAgeRating(applicationAgeRating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationGenreRating(ApplicationGenreRating applicationGenreRating) {
        this.cassandraRepository.insertApplicationGenreRating(applicationGenreRating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertApplicationDateRating(ApplicationDateRating applicationDateRating) {
        this.cassandraRepository.insertApplicationDateRating(applicationDateRating);
    }

    @Override
    public void insertApplicationVersionFeeling(ApplicationVersionFeeling applicationVersionFeeling) {
        this.cassandraRepository.insertApplicationVersionFeeling(applicationVersionFeeling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeConnection() {
        try {
            cassandraConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
