package cassandra.service;

import model.ApplicationPriceRating;

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

}
