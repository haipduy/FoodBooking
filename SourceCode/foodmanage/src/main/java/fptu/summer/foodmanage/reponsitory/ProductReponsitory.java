package fptu.summer.foodmanage.reponsitory;

import fptu.summer.foodmanage.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
@Repository
public interface ProductReponsitory extends JpaRepository<ProductEntity, String> {

    ProductEntity findByProNameContaining(String searchValue);

    ProductEntity findByProCode(String productCode);

//    List<ProductEntity> findAll();

    List<ProductEntity> findAllByProStatus(int status);

    boolean existsDistinctByProCode(String proCode);

}
