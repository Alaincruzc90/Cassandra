package model;

public class ApplicationGenreRating {

    private String genre;
    private Float rating;
    private Integer reviewCount;
    private String installs;
    private String category;
    private String name;

    public ApplicationGenreRating(String genre, Float rating, Integer reviewCount, String installs, String category, String name) {
        this.genre = genre;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.installs = installs;
        this.category = category;
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
