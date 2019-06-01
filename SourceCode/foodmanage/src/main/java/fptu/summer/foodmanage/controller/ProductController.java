package fptu.summer.foodmanage.controller;

import fptu.summer.foodmanage.entity.CategoryEntity;
import fptu.summer.foodmanage.entity.ProductEntity;
import fptu.summer.foodmanage.entity.StoreEntity;
import fptu.summer.foodmanage.reponsitory.CategoryReponsitory;
import fptu.summer.foodmanage.reponsitory.ProductReponsitory;
import fptu.summer.foodmanage.reponsitory.StoreReponsitory;
import fptu.summer.foodmanage.responsemodel.ProductResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductReponsitory productReponsitory;

    @Autowired
    private CategoryReponsitory categoryReponsitory;
    @Autowired
    private StoreReponsitory storeReponsitory;

    @GetMapping("/products")
    public ResponseEntity getAllProductByStatus() {


        List<ProductResponseModel> listReponse = new ArrayList<>();

        List<ProductEntity> listProduct = productReponsitory.findAllByProStatus(1);

        if (listProduct != null) {
            for (ProductEntity product : listProduct) {

                String proId = product.getProId();
                StoreEntity store = storeReponsitory.findByStoreId(product.getStoreId());
                String storeName = store.getStoreName();
                String proName = product.getProName();
                float proPrice = product.getProPrice();
                float priceDiscount = product.getPriceDiscount();
                String proImage = product.getProImage();
                int proQuantity = product.getProQuantity();
                String proDescription = product.getProDescription();
                int proStatus = product.getProStatus();
                CategoryEntity category = categoryReponsitory.findByCategoryId(product.getCategoryId());

                ProductResponseModel pro = new ProductResponseModel(proId, storeName, proName, proPrice, priceDiscount, proImage, proQuantity, proDescription, proStatus, category);

                listReponse.add(pro);

            }
            return new ResponseEntity(listReponse, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/product/create")
    public ResponseEntity createNewProduct(@RequestBody ProductEntity product) {

        String productCode = product.getProId();
        int id = product.getCategoryId();

        boolean checkProductCode = productReponsitory.existsDistinctByProId(productCode);
        boolean checkcategory = categoryReponsitory.existsDistinctByCategoryId(id);

        if (checkProductCode == true || checkcategory == false) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        product.setProStatus(1);
        productReponsitory.save(product);

        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity updateProduct(@RequestBody ProductEntity product) {
        String productCode = product.getProId();
        ProductEntity productEntity = productReponsitory.findByProId(productCode);

        if (productEntity != null) {
            productEntity.setProName(product.getProName());
            productEntity.setProPrice(product.getProPrice());
            productEntity.setProImage(product.getProImage());
            productEntity.setProQuantity(product.getProQuantity());
            productEntity.setProDescription(product.getProDescription());

            productReponsitory.save(productEntity);
            return new ResponseEntity(productEntity, HttpStatus.OK);

        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/product/changestatus/{id}")
    public ResponseEntity changeStatus(@PathVariable String id) {

        ProductEntity product = productReponsitory.findByProId(id);
        if (product != null) {
            product.setProStatus(0);
            productReponsitory.save(product);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
}
