package model;

public class CategoryOneTotal {

    private Integer categoryOneId;
    private Integer total;

    public CategoryOneTotal() {}

    public CategoryOneTotal(Integer categoryOneId, Integer total) {
        this.categoryOneId = categoryOneId;
        this.total = total;
    }

    public Integer getCategoryOneId() {
        return categoryOneId;
    }

    public void setCategoryOneId(Integer categoryOneId) {
        this.categoryOneId = categoryOneId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
