package fptu.summer.foodmanage.controller;


import fptu.summer.foodmanage.entity.CategoryEntity;
import fptu.summer.foodmanage.reponsitory.CategoryReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    CategoryReponsitory categoryReponsitory;

    @GetMapping("category/{id}")
    public ResponseEntity getCategoryNameById(@PathVariable int id){
        if(categoryReponsitory.existsDistinctByCategoryId(id)){
            CategoryEntity cate = categoryReponsitory.findByCategoryId(id);
            return new ResponseEntity(cate, HttpStatus.OK);
        }
        return  new ResponseEntity( HttpStatus.BAD_REQUEST);
    }


}
