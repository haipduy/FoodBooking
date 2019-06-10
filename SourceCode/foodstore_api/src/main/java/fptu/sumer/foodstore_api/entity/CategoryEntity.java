package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;

@Entity
@Table(name = "Category", schema = "dbo", catalog = "FoodSystem")
public class CategoryEntity {
    private int categoryId;
    private String categoryName;

    public CategoryEntity() {
    }

    @Id
    @Column(name = "CategoryId")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "CategoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
