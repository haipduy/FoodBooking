package fptu.sumer.foodstore_api.controller;


import fptu.sumer.foodstore_api.entity.CategoryEntity;
import fptu.sumer.foodstore_api.reponsitory.CategoryRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/v1/")
@Api(value = "Account Management System")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("category/{id}")
    public ResponseEntity getCategoryNameById(@PathVariable int id){
        if(categoryRepository.existsDistinctByCategoryId(id)){
            CategoryEntity cate = categoryRepository.findByCategoryId(id);
            return new ResponseEntity(cate, HttpStatus.OK);
        }
        return  new ResponseEntity( HttpStatus.BAD_REQUEST);
    }


}
