package fptu.sumer.foodstore_api.controller;

import fptu.sumer.foodstore_api.entity.AccountEntity;
import fptu.sumer.foodstore_api.reponsitory.AccountRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Account Management System")
public class AccountController {

    @Autowired
    AccountRepository ar;

    @ApiOperation(value = "View a list of available account", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping("accounts")
    public ResponseEntity getAllCustomer() {

        List<AccountEntity> listCus = ar.findAllByStatus(1);
        if (listCus != null) {
            return new ResponseEntity(listCus, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Check login by username and password")
    @PostMapping("account/login")
    public ResponseEntity<AccountEntity> checklogin(
            @ApiParam(value = "Check login by username and password", required = true) @RequestBody Map<String, String> account
    ) {
        String username = account.get("username");
        String password = account.get("password");

        AccountEntity accountEntity = ar.findAccountEntitiesByUserIdAndUserPasswordAndStatus(username,password,1);

        if (accountEntity != null) {
            return new ResponseEntity(accountEntity, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Create new account")
    @PostMapping("accounts")
    public ResponseEntity createNewAccount(
            @ApiParam(value = "Account object store in database table", required = true) @RequestBody AccountEntity account
    ){

        String username = account.getUserId();

        if(ar.existsDistinctByUserId(username) == true ){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        account.setStatus(1);
        account.setRoleId(2);

        ar.save(account);

//        return new ResponseEntity(HttpStatus.OK);
        return ResponseEntity.ok(account);
    }

    @ApiOperation(value = "Update account by Id")
    @PutMapping("accounts/{id}")
    public ResponseEntity updateAccountById(
//            @RequestBody AccountEntity account
            @ApiParam(value = "Account Id to update account object", required = true) @PathVariable(value = "id") String id,
            @ApiParam(value = "Update account object", required = true) @RequestBody AccountEntity account
    ) {

        AccountEntity accountEntity = ar.findAccountEntitiesByUserId(id);

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
}