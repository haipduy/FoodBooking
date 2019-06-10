package fptu.sumer.foodstore_api.controller;


import fptu.sumer.foodstore_api.entity.OrderEntity;
import fptu.sumer.foodstore_api.reponsitory.DetailOrderReponsitory;
import fptu.sumer.foodstore_api.reponsitory.OrderReponsitory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Account Management System", description = "Operations pertaining to account in Account Management System")
public class OrderController {

    @Autowired
    OrderReponsitory orderReponsitory;
    @Autowired
    DetailOrderReponsitory detailOrderReponsitory;


    @GetMapping("orders")
    public ResponseEntity getAllOrder(){
        return  null;
    }


    @PostMapping("order/create")
    public ResponseEntity updateOrderById(@RequestBody OrderEntity order){

        return  null;
    }



    @PutMapping("order/update/{id}")
    public ResponseEntity createNewOrder(@PathVariable int id, @RequestBody OrderEntity order){

        return  null;

    }




}
