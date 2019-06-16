package fptu.sumer.foodstore_api.reponsitory;

import fptu.sumer.foodstore_api.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

//    List<PaymentEntity> findAll();
}
