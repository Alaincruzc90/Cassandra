package dataprocessing;

import cassandra.service.implementation.CassandraServiceImplementation;
import model.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DataProcessing {

    private CassandraServiceImplementation cassandraServiceImplementation;

    public DataProcessing() {
        this.cassandraServiceImplementation = new CassandraServiceImplementation();
    }

    public void insertData() throws Exception {
        // This will store all the rows inserted to report at the end of execution
        int rowsInserted = 0;

        // Get and print the current time.
        long start = System.currentTimeMillis();
        System.out.println("Starting execution at: " + Calendar.getInstance().getTime());

        // First read from the required data.
        BufferedReader googlePlayStoreData;
        BufferedReader googlePlayStoreReviewData;
        try {
            googlePlayStoreData = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("data/googleplaystore.csv")));
            googlePlayStoreReviewData = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("data/googleplaystore_user_reviews.csv")));
        } catch (NullPointerException e) {
            cassandraServiceImplementation.closeConnection();
            throw new Exception("Error al leer los archivos CSV.");
        }

        // Iterate through all the lines of the google applications' data.
        // We'll save certain info in a map to be used when we parse the second csv
        Map<String, TempApplicationData> tempApplicationData = new HashMap<>();

        BufferedReader br = new BufferedReader(googlePlayStoreData);
        String line;
        br.readLine(); // Skip the first line.
        while ((line = br.readLine()) != null) {

            // Separate all values into an array of Strings.
            String[] values = line.split(",");

            // Transform each value into an understandable variable.
            // Some transformations may need to be check if they are parsable,
            // else we are going to save a 0. In case of Strings, if they are null,
            // then we are going to insert an empty String.
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

            // The size is a string with an appended M at the end most of the time
            // Otherwise is it exactly "Varies with size", in which case we'll insert a 0
            // We will ignore the m and try to parse it as a double
            String sizeString = Objects.toString(values[4], "").substring(0, values[4].length() - 1);
            Double size;
            try{
                size = Double.parseDouble(sizeString);
            } catch ( NumberFormatException  e) {
                System.out.println("Could not parse size " + sizeString + " to double for application "+name+".\nInserting 0 instead.");
                size = 0.0;
            }

            String installs = Objects.toString(values[5], "");
            String type = Objects.toString(values[6], "");
            double price = NumberUtils.toDouble(values[7], 0);
            String contentRating = Objects.toString(values[8], "");
            String genre = Objects.toString(values[9], "");

            // Fetch date and transform it into a format Cassandra understands.
            // Only format the date if it is different that 0.
            String lastUpdate = Objects.toString(values[10], "");
            if(!lastUpdate.equals("")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yy", Locale.ENGLISH);
                LocalDate dateTime;
                try {
                    // Attempt to parse the date and then convert it to string
                    dateTime = LocalDate.parse(lastUpdate, formatter);
                    lastUpdate = dateTime.toString();
                } catch(DateTimeParseException e) {
                    // If the date was incorrect, insert an empty string
                    lastUpdate = "";
                }

            }

            String currentVersion = Objects.toString(values[11], "");
            String androidVersion = Objects.toString(values[12], "");

            // Now, create the needed models.
            ApplicationPriceRating applicationPriceRating = new ApplicationPriceRating(name, type, price, rating, installs, reviewCount);
            ApplicationSizeRating applicationSizeRating = new ApplicationSizeRating(name, size, rating, reviewCount, installs);
            ApplicationAgeRating applicationAgeRating = new ApplicationAgeRating(contentRating, rating, reviewCount, installs);
            ApplicationGenreRating applicationGenreRating = new ApplicationGenreRating(genre, rating, reviewCount, installs, category, name);
            ApplicationDateRating applicationDateRating  = new ApplicationDateRating (name, lastUpdate, rating, installs, reviewCount);
            ApplicationCategoryRating applicationCategoryRating = new ApplicationCategoryRating(category, rating, reviewCount, installs);
            ApplicationCategory applicationCategory = new ApplicationCategory(category, name);

            // Insert the new data.
            cassandraServiceImplementation.insertApplicationPriceRating(applicationPriceRating);
            cassandraServiceImplementation.insertApplicationSizeRating(applicationSizeRating);
            cassandraServiceImplementation.insertApplicationAgeRating(applicationAgeRating);
            cassandraServiceImplementation.insertApplicationGenreRating(applicationGenreRating);
            cassandraServiceImplementation.insertApplicationDateRating(applicationDateRating);
            cassandraServiceImplementation.insertApplicationCategoryRating(applicationCategoryRating);
            cassandraServiceImplementation.insertApplicationCategory(applicationCategory);
            rowsInserted += 7;

            // Store necessary data for when we parse the second csv
            tempApplicationData.put(name, new TempApplicationData(category, androidVersion, rating, reviewCount, installs));
        }

        // Parse the second csv
        br = new BufferedReader(googlePlayStoreReviewData);
        br.readLine(); // Skip the first line.
        while ((line = br.readLine()) != null) {
            // Separate all values into an array of Strings.
            String[] values = line.split(",");

            // Transform each value into an understandable variable.
            // Some transformations may need to be check if they are parsable,
            // else we are going to save a 0. In case of Strings, if they are null,
            // then we are going to insert an empty String.
            String name = values[0];

            if (name == null || !tempApplicationData.containsKey(name)) {
                // If the name is null or we have no application data for this review, skip this line.
                continue;
            } else {
                name = name.replace("'", "''");
            }

            String sentimentString = values[2];
            Float sentimentPolarity, sentimentSubjectivity;
            try {
                sentimentPolarity = Float.parseFloat(values[3]);
                sentimentSubjectivity =  Float.parseFloat(values[4]);
            } catch(NumberFormatException e) {
                sentimentPolarity = 0f;
                sentimentSubjectivity = 0f;
            }

            // Retrieve the info for the app to which this review belongs, to be used when creating the models
            TempApplicationData applicationData = tempApplicationData.get(name);

            // Create the needed models
            ApplicationVersionFeeling applicationVersionFeeling = new ApplicationVersionFeeling(
                    applicationData.getAndroidVersion(), sentimentPolarity, applicationData.getRating(),
                    applicationData.getReviewCount(), applicationData.getInstalls(), name);
            ApplicationFeelingRating applicationFeelingRating = new ApplicationFeelingRating(
                    name, sentimentString, applicationData.getRating(),
                    applicationData.getReviewCount());


            // Insert the data
            cassandraServiceImplementation.insertApplicationVersionFeeling(applicationVersionFeeling);
            cassandraServiceImplementation.insertApplicationFeelingRating(applicationFeelingRating);

            rowsInserted += 2;
        }

        // Get the time at the end of execution
        long end = System.currentTimeMillis();

        // Calculate and report duration
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds to insert " + rowsInserted + " rows.");

        cassandraServiceImplementation.closeConnection();
    }
}

class TempApplicationData {

    private String category;
    private String androidVersion;
    private Float rating;
    private Integer reviewCount;
    private String installs;

    public TempApplicationData(String category, String androidVersion, Float rating, Integer reviewCount, String installs) {
        this.category = category;
        this.androidVersion = androidVersion;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.installs = installs;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public Float getRating() {
        return rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public String getInstalls() {
        return installs;
    }

}
