package fptu.summer.foodmanage.reponsitory;

import fptu.summer.foodmanage.entity.DetailOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailOrderReponsitory extends JpaRepository<DetailOrderEntity, Integer> {


}
