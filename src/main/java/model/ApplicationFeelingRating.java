package model;

public class ApplicationFeelingRating {

    private String name;
    private String feeling;
    private Float rating;
    private Integer reviewCount;

    public ApplicationFeelingRating(String name, String feeling, Float rating, Integer reviewCount) {
        this.name = name;
        this.feeling = feeling;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
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

}
