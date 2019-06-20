package model;

public class ApplicationCategoryRating {

    private String category;
    private Float rating;
    private Integer reviewCount;
    private String installs;

    public ApplicationCategoryRating(String category, Float rating, Integer reviewCount, String installs) {
        this.category = category;
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
