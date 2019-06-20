package model;

public class ApplicationAgeRating {
    private String ageClasification;
    private Float rating;
    private Integer reviewCount;
    private String installs;

    public ApplicationAgeRating(String ageClasification, Float rating, Integer reviewCount, String installs) {
        this.ageClasification = ageClasification;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.installs = installs;
    }

    public String getAgeClasification() {
        return ageClasification;
    }

    public void setAgeClasification(String ageClasification) {
        this.ageClasification = ageClasification;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getInstalls() {
        return installs;
    }

    public void setInstalls(String installs) {
        this.installs = installs;
    }

}
