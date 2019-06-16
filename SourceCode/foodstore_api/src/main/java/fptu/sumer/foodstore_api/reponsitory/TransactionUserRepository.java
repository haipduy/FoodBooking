package fptu.sumer.foodstore_api.reponsitory;

import fptu.sumer.foodstore_api.entity.TransactionUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionUserRepository extends JpaRepository<TransactionUserEntity,Integer> {

}
