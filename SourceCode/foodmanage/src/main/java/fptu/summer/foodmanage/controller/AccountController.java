package fptu.summer.foodmanage.controller;

import fptu.summer.foodmanage.entity.AccountEntity;
import fptu.summer.foodmanage.reponsitory.AccountReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

public class AccountController {

    @Autowired
    AccountReponsitory ar;

    @GetMapping("accounts")
    public ResponseEntity getAllCustomer() {

        List<AccountEntity> listCus = ar.findAllByStatus(1);
        if(listCus!=null){
            return new ResponseEntity(listCus, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("account/login")
    public ResponseEntity login(@RequestBody Map<String, String> account) {

        String username = account.get("username");
        String password = account.get("password");

        AccountEntity accountEntity = ar.findAccountEntitiesByUserCode(username);
        if(accountEntity != null){
            if(accountEntity.getStatus() == 1 && accountEntity.getUserPassword().equals(password)){
                return new ResponseEntity(accountEntity, HttpStatus.OK);
            }
        }
        return new ResponseEntity( HttpStatus.BAD_REQUEST);
    }
    @PostMapping("account/create")
    public ResponseEntity createNewAccount(@RequestBody  AccountEntity account){

        String username = account.getUserCode();

        if(ar.existsDistinctByUserCode(username) == true ){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        account.setStatus(1);
        account.setRoleId(2);

        ar.save(account);

//        return new ResponseEntity(HttpStatus.OK);
        return ResponseEntity.ok(account);
    }

    @PutMapping("account/update")
    public ResponseEntity updateAccountById(@RequestBody AccountEntity account) {
        String username = account.getUserCode();

        AccountEntity accountEntity = ar.findAccountEntitiesByUserCode(username);

        if(accountEntity != null){
            accountEntity.setUserName(account.getUserName());
            accountEntity.setUserAddress(account.getUserAddress());
            accountEntity.setPhone(account.getPhone());
            accountEntity.setEmail(account.getEmail());
            ar.save(accountEntity);
            return new ResponseEntity(accountEntity, HttpStatus.OK);
        }

       return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("account/changestatus/{id}")
    public ResponseEntity changerStatus(@PathVariable String id) {

        AccountEntity accountEntity = ar.findAccountEntitiesByUserCode(id);

        if(accountEntity != null){
            accountEntity.setStatus(0);
            ar.save(accountEntity);
            return new ResponseEntity(accountEntity, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
