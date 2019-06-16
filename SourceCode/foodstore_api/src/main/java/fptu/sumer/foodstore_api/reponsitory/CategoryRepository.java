package fptu.sumer.foodstore_api.reponsitory;



import fptu.sumer.foodstore_api.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    boolean existsDistinctByCategoryId(int id);

    CategoryEntity  findByCategoryId(int id);

}
