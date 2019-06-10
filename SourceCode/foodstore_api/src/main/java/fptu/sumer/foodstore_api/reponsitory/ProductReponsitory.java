package fptu.sumer.foodstore_api.reponsitory;


import fptu.sumer.foodstore_api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Service
@Repository
public interface ProductReponsitory extends JpaRepository<ProductEntity, String> {

    ProductEntity findByProNameContaining(String searchValue);

    ProductEntity findByProId(String productCode);

//    List<ProductEntity> findAll();

    List<ProductEntity> findAllByProStatus(int status);

    boolean existsDistinctByProId(String proCode);

}
