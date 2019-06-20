package model;

public class ApplicationPriceRating {

    private String name;
    private String type;
    private Double price;
    private Float rating;
    private String installs;
    private Integer reviewCount;

    public ApplicationPriceRating(String name, String type, Double price, Float rating, String installs, Integer reviewCount) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.rating = rating;
        this.installs = installs;
        this.reviewCount = reviewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getInstalls() {
        return installs;
    }

    public void setInstalls(String installs) {
        this.installs = installs;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

}
