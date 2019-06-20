package dataprocessing;

import cassandra.service.implementation.CassandraServiceImplementation;
import model.ApplicationPriceRating;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

public class DataProcessing {

    private CassandraServiceImplementation cassandraServiceImplementation;

    public DataProcessing() {
        this.cassandraServiceImplementation = new CassandraServiceImplementation();
    }

    public void insertData() throws Exception {

        // First read from the required data.
        File googlePlayStoreData;
        File googlePlayStoreReviewData;
        try {
            googlePlayStoreData = new File(getClass().getClassLoader().getResource("data/googleplaystore.csv").getFile());
            googlePlayStoreReviewData = new File(getClass().getClassLoader().getResource("data/googleplaystore_user_reviews.csv").getFile());
        } catch (NullPointerException e) {
            cassandraServiceImplementation.closeConnection();
            throw new Exception("Error al leer los archivos CSV.");
        }

        // Iterate through all the lines of the google applications' data.
        BufferedReader br = new BufferedReader(new FileReader(googlePlayStoreData));
        String line;
        br.readLine(); // Skip the first line.
        while ((line = br.readLine()) != null) {

            // Separate all values into an array of Strings.
            String[] values = line.split(",");

            // Transform each value into an understandable variable.
            // Some transformations may need to be check if they are parsable,
            // else we are going to save an 0.
            String name = values[0];

            if (name == null) {
                // If the name is null we just skip this line.
                continue;
            } else {
                name = name.replace("'", "''");
            }

            String category = Objects.toString(values[1], "");
            float rating = NumberUtils.toFloat(values[2], 0);
            int reviewCount = NumberUtils.toInt(values[3], 0);
            String size = Objects.toString(values[4], "");
            String installs = Objects.toString(values[5], "");
            String type = Objects.toString(values[6], "");
            double price = NumberUtils.toDouble(values[7], 0);
            String contentRating = Objects.toString(values[8], "");
            String genre = Objects.toString(values[9], "");
            String lastUpdate = Objects.toString(values[10], "");
            String currentVersion = Objects.toString(values[11], "");
            String androidVersion = Objects.toString(values[12], "");

            // Now, created the needed models.
            ApplicationPriceRating applicationPriceRating = new ApplicationPriceRating(name, type, price, rating, installs, reviewCount);

            // Add the new data.
            cassandraServiceImplementation.insertApplicationPriceRating(applicationPriceRating);

        }

        cassandraServiceImplementation.closeConnection();
    }
}
