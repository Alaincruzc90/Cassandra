package model;

public class ApplicationSizeRating {
    private String name;
    private Double size;
    private Float rating;
    private Integer reviewCount;
    private String installs;

    public ApplicationSizeRating(String name, Double size, Float rating, Integer reviewCount, String installs) {
        this.name = name;
        this.size = size;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.installs = installs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
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
