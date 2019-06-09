package fptu.summer.foodmanage.controller;


import fptu.summer.foodmanage.entity.OrderEntity;
import fptu.summer.foodmanage.reponsitory.DetailOrderReponsitory;
import fptu.summer.foodmanage.reponsitory.OrderReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
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
