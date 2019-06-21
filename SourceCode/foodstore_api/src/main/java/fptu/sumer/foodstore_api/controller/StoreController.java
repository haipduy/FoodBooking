package fptu.sumer.foodstore_api.controller;


import fptu.sumer.foodstore_api.entity.StoreEntity;
import fptu.sumer.foodstore_api.reponsitory.StoreRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Account Management System", description = "Operations pertaining to account in Account Management System")
public class StoreController {

    @Autowired
    private StoreRepository storeReponsitory;


    @GetMapping("stores")
    public ResponseEntity getAllStore() {

        List<StoreEntity> storesList = storeReponsitory.findAllByStoreStatus(1);
        if (storesList != null) {
            return new ResponseEntity(storesList, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("stores/{id}")
    public ResponseEntity getStoreById(
            @PathVariable String id
    ) {

        StoreEntity storeEntity = storeReponsitory.findByStoreId(id);

        if (storeEntity != null) {
            return new ResponseEntity(storeEntity, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("stores")
    public ResponseEntity createNewStore(
            @RequestBody StoreEntity store
    ) {
        String storeCode = store.getStoreId();

        if (storeReponsitory.existsDistinctByStoreId(storeCode) == true) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        store.setStoreStatus(1);
        storeReponsitory.save(store);
        return new ResponseEntity(store, HttpStatus.OK);
    }

    @PutMapping("stores/{id}")
    public ResponseEntity changeStatusStore(
            @PathVariable String id,
            @RequestBody StoreEntity store
    ) {

        StoreEntity storeEntity = storeReponsitory.findByStoreId(id);

        if (storeEntity != null) {
            storeEntity.setStoreStatus(0);
            storeReponsitory.save(storeEntity);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
