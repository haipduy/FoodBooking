package fptu.summer.foodmanage.controller;

import fptu.summer.foodmanage.entity.StoreEntity;
import fptu.summer.foodmanage.reponsitory.StoreReponsitory;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {
    @Autowired
    private  StoreReponsitory storeReponsitory;


    @GetMapping("/stores")
    public ResponseEntity getAllStore(){

        List<StoreEntity> storesList = storeReponsitory.findAllByStoreStatus(1);
        if(storesList!=null){
            return new ResponseEntity(storesList, HttpStatus.OK);
        }

        return  new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/store/createstore")
    public ResponseEntity createNewStore(@RequestBody StoreEntity store){

        String storeCode  =  store.getStoreCode();



        if(storeReponsitory.existsDistinctByStoreCode(storeCode) == true){
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        store.setStoreStatus(1);
        storeReponsitory.save(store);
        return new ResponseEntity(store, HttpStatus.OK);
    }

    @PutMapping("/store/changestatus/{id}")
    public ResponseEntity changeStatusStore(@PathVariable String id) {

        StoreEntity store = storeReponsitory.findByStoreCode(id);

        if(store!= null){
            store.setStoreStatus(0);
            storeReponsitory.save(store);
            return  new ResponseEntity(HttpStatus.OK);
        }
        return  new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
