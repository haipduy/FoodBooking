package fptu.sumer.foodstore_api.reponsitory;


import fptu.sumer.foodstore_api.entity.DetailOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrderEntity, Integer> {


}
