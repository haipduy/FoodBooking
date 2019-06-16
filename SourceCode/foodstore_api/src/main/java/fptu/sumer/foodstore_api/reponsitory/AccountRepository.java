package fptu.sumer.foodstore_api.reponsitory;

import fptu.sumer.foodstore_api.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String> {

    // select all account from DB
    List<AccountEntity> findAllByStatus(int status);

    // find Account by user code
    AccountEntity findAccountEntitiesByUserId(String userId);
    AccountEntity findAccountEntitiesByUserIdAndUserPasswordAndStatus(String username,String password, int status);

    // check account is exist
    boolean existsDistinctByUserId(String username);

}
