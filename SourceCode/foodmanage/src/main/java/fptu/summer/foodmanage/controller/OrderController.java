package fptu.summer.foodmanage.controller;


import fptu.summer.foodmanage.reponsitory.OrderReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    @Autowired
    OrderReponsitory orderReponsitory;
}
