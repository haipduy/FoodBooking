package fptu.summer.foodmanage.controller;

import fptu.summer.foodmanage.entity.ProductEntity;
import fptu.summer.foodmanage.reponsitory.CategoryReponsitory;
import fptu.summer.foodmanage.reponsitory.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductReponsitory productReponsitory;
    @Autowired
    private CategoryReponsitory categoryReponsitory;

    @GetMapping("/products")
    public ResponseEntity getAllProductByStatus() {

        List<ProductEntity> listProduct = productReponsitory.findAllByProStatus(1);

        if (listProduct != null) {
            return new ResponseEntity(listProduct, HttpStatus.OK);

        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/product/create")
    public ResponseEntity createNewProduct(@RequestBody ProductEntity product) {

        String productCode = product.getProCode();
        int id = product.getCategoryId();

        boolean checkProductCode = productReponsitory.existsDistinctByProCode(productCode);
        boolean checkCategory = categoryReponsitory.existsDistinctByCategoryId(id);

        if (checkProductCode == true || checkCategory == true) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        product.setProStatus(1);
        productReponsitory.save(product);


        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity updateProduct(@RequestBody ProductEntity product) {
        String productCode = product.getProCode();
        ProductEntity productEntity = productReponsitory.findByProCode(productCode);

        if (productEntity != null) {
            productEntity.setProName(product.getProName());
            productEntity.setProPrice(product.getProPrice());
            productEntity.setProImage(product.getProImage());
            productEntity.setProQuantity(product.getProQuantity());
            productEntity.setProDescription(product.getProDescription());

            productReponsitory.save(productEntity);
            return new ResponseEntity(productEntity, HttpStatus.OK);

        }

        return new ResponseEntity( HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/product/changestatus/{id}")
    public ResponseEntity changeStatus(@PathVariable String id) {

        ProductEntity product = productReponsitory.findByProCode(id);
        if(product!= null){
            product.setProStatus(0);
            productReponsitory.save(product);
            return  new ResponseEntity(HttpStatus.OK);
        }
        return  new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
}
