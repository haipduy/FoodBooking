package fptu.sumer.foodstore_api.reponsitory;


import fptu.sumer.foodstore_api.entity.OrderListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderListEntity, Integer> {
    //get all order
    List<OrderListEntity>findAll();

    //get order by code
    OrderListEntity findAllByOrderId(String orderId);
//
//    OrderListEntity saveOrder(String username);
    // get all order by user id
    List<OrderListEntity> findAllByUserId(String userId);

    // Save order

}
