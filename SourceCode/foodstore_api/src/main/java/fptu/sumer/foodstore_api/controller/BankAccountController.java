package fptu.sumer.foodstore_api.controller;


import fptu.sumer.foodstore_api.entity.BankAccountEntity;
import fptu.sumer.foodstore_api.reponsitory.BankAccountRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Bank Account Management System")
public class BankAccountController {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @GetMapping("bankaccounts/{id}")
    public ResponseEntity getBankAccountByUserId(@PathVariable String id) {

        BankAccountEntity  bankAccountEntity = bankAccountRepository.findByUserIdAndIsActive(id,1);
        if(bankAccountEntity!=null){

            return ResponseEntity.ok(bankAccountEntity);
        }

        return  new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Create new account")
    @PostMapping("bankaccounts")
    public ResponseEntity createNewAccount(@RequestBody BankAccountEntity bankAccountEntity){

        bankAccountRepository.save(bankAccountEntity);

        return ResponseEntity.ok(bankAccountEntity);
    }
}
