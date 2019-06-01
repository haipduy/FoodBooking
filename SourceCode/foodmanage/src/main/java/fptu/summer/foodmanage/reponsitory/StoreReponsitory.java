package fptu.summer.foodmanage.reponsitory;

import fptu.summer.foodmanage.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreReponsitory extends JpaRepository<StoreEntity, String> {

    List<StoreEntity> findAllByStoreStatus(int status);

    StoreEntity findByStoreId(String storeCode);

    boolean existsDistinctByStoreId(String storeCode);

    String findByStoreName(String storeCode);




}
