package fptu.sumer.foodstore_api.controller;

import fptu.sumer.foodstore_api.entity.CategoryEntity;
import fptu.sumer.foodstore_api.entity.ProductEntity;
import fptu.sumer.foodstore_api.entity.StoreEntity;
import fptu.sumer.foodstore_api.reponsitory.CategoryRepository;
import fptu.sumer.foodstore_api.reponsitory.ProductRepository;
import fptu.sumer.foodstore_api.reponsitory.StoreRepository;
import fptu.sumer.foodstore_api.responsemodel.ProductResponseModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Account Management System", description = "Operations pertaining to account in Account Management System")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("products")
    public ResponseEntity getAllProductByStatus() {

        List<ProductResponseModel> listReponse = new ArrayList<>();

        List<ProductEntity> listProduct = productRepository.findAllByProStatus(1);

        if (listProduct != null) {
            for (ProductEntity product : listProduct) {

                String proId = product.getProId();
                String storeId= product.getStoreId();

                StoreEntity store = storeRepository.findByStoreId(storeId);

                String storeName = store.getStoreName();
                String proName = product.getProName();
                float proPrice = product.getProPrice();
                float priceDiscount = product.getPriceDiscount();
                String proImage = product.getProImage();
                int proQuantity = product.getProQuantity();
                String proDescription = product.getProDescription();
                int proStatus = product.getProStatus();
                CategoryEntity category = categoryRepository.findByCategoryId(product.getCategoryId());

                ProductResponseModel pro = new ProductResponseModel(proId, storeId , storeName, proName, proPrice, priceDiscount, proImage, proQuantity, proDescription, proStatus, category);

                listReponse.add(pro);

            }
            return new ResponseEntity(listReponse, HttpStatus.OK);
//            return new ResponseEntity(listProduct, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("products/{id}")
    public ResponseEntity getProductById(
            @PathVariable String id
    ) {
        ProductEntity productEntity = productRepository.findByProId(id);
        if(productEntity!=null){
            return new ResponseEntity(productEntity, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("products")
    public ResponseEntity createNewProduct(
            @RequestBody ProductEntity product
    ) {

        String productCode = product.getProId();
        int id = product.getCategoryId();

        boolean checkProductCode = productRepository.existsDistinctByProId(productCode);
        boolean checkcategory = categoryRepository.existsDistinctByCategoryId(id);

        if (checkProductCode == true || checkcategory == false) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        product.setProStatus(1);
        productRepository.save(product);

        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PutMapping("products/{id}")
    public ResponseEntity updateProduct(
            @PathVariable String id,
            @RequestBody ProductEntity product
    ) {
        ProductEntity productEntity = productRepository.findByProId(id);

        if (productEntity != null) {

            productEntity.setProName(product.getProName());
            productEntity.setProPrice(product.getProPrice());
            productEntity.setProImage(product.getProImage());
            productEntity.setProQuantity(product.getProQuantity());
            productEntity.setProDescription(product.getProDescription());
            productEntity.setProStatus(product.getProStatus());

            productRepository.save(productEntity);
            return new ResponseEntity(productEntity, HttpStatus.OK);

        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

}
