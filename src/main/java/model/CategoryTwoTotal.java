package model;

public class CategoryTwoTotal {

    private Integer categoryTwoId;
    private Integer total;

    public CategoryTwoTotal() {}

    public CategoryTwoTotal(Integer categoryTwoId, Integer total) {
        this.categoryTwoId = categoryTwoId;
        this.total = total;
    }

    public Integer getCategoryTwoId() {
        return categoryTwoId;
    }

    public void setCategoryTwoId(Integer categoryTwoId) {
        this.categoryTwoId = categoryTwoId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
