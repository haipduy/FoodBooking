package fptu.sumer.foodstore_api.reponsitory;

import fptu.sumer.foodstore_api.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {

    BankAccountEntity findByUserIdAndIsActive(String userId, int isActive);

}
