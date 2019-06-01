package fptu.summer.foodmanage.reponsitory;


import fptu.summer.foodmanage.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReponsitory extends JpaRepository<CategoryEntity, Integer> {

    boolean existsDistinctByCategoryId(int id);

    CategoryEntity  findByCategoryId(int id);

}
