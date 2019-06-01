package fptu.summer.foodmanage.reponsitory;

import fptu.summer.foodmanage.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReponsitory extends JpaRepository<OrderEntity, Integer> {
    //get all order
    List<OrderEntity>findAll();

    //get order by code


    // get all order by user id


    // Save order
}
