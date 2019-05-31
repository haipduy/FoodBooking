package fptu.summer.foodmanage.controller;


import fptu.summer.foodmanage.reponsitory.CategoryReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller

public class CategoryController {

    @Autowired
    CategoryReponsitory categoryReponsitory;

//    @GetMapping
//    public ResponseEntity getAll


}
