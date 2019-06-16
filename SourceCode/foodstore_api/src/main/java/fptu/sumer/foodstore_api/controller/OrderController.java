package fptu.sumer.foodstore_api.controller;


import fptu.sumer.foodstore_api.entity.*;
import fptu.sumer.foodstore_api.reponsitory.*;
import fptu.sumer.foodstore_api.responsemodel.ItemResponseModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Order Management")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DetailOrderRepository detailOrderRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    TransactionUserRepository transactionUserRepository;


    @GetMapping("/orders")
    public ResponseEntity getAllOrder() {
        List<OrderListEntity> listOrderListEntity = orderRepository.findAll();
        if (listOrderListEntity != null) {
//            return new ResponseEntity(listOrderListEntity, HttpStatus.OK);
            return ResponseEntity.ok(listOrderListEntity);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //get order by order ID
    @GetMapping("orders/{id}")
    public ResponseEntity getOrderById(
            @PathVariable String orderId
    ) {
        OrderListEntity orderListEntity = orderRepository.findAllByOrderId(orderId);
        if (orderListEntity != null) {
            return ResponseEntity.ok(orderListEntity);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //get order by userID
    @GetMapping("accounts/{id}/orders")
    public ResponseEntity getOrderByCuID(
            @PathVariable String cusId
    ) {
        List<OrderListEntity> listOrderListEntity = orderRepository.findAllByUserId(cusId);
        if (listOrderListEntity != null) {
            return new ResponseEntity(listOrderListEntity, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //create new order
    @PostMapping("/orders")
    public ResponseEntity updateOrderById(
            @PathVariable String userId,
            @PathVariable float total,
            @PathVariable String notes,
            @RequestBody List<ItemResponseModel> listProduct
    ) {
        //get bank account by id
        BankAccountEntity bankAccount = getAccountByUserId(userId);

        // chack tai khoan con tien hay k
        float sodu = bankAccount.getAccMoney();
        if (sodu >= total) {
            bankAccount.setAccMoney(sodu - total);
            bankAccountRepository.saveAndFlush(bankAccount);
            int bankid = bankAccount.getBankId();

            //create new order
            OrderListEntity order = new OrderListEntity();
            order.setUserId(userId);
            Date orderDate = new Date(System.currentTimeMillis());
            order.setOrderDate(orderDate);
            order.setTotal(total);
            order.setNotes(notes);
            orderRepository.saveAndFlush(order);
//
            //get id order
            int orderId = order.getOrderId();

            //create order detail
            createNewDetailOrder(orderId, listProduct);
            ///create payment and get payid
            int payId = createPayment(orderId,total,orderDate);
            // create transaction
            createTransaction(bankid,payId,orderDate);

            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/order/{id}")
    public ResponseEntity createNewOrder(@PathVariable int id, @RequestBody OrderListEntity order) {

        return null;

    }


    private BankAccountEntity getAccountByUserId(String userId) {
        BankAccountEntity bankAccount = new BankAccountEntity();
        bankAccount = bankAccountRepository.findByUserIdAndIsActive(userId, 1);
        return bankAccount;
    }

    private void createNewDetailOrder(int orderId, List<ItemResponseModel> listProduct) {
        DetailOrderEntity detailOrder  = new DetailOrderEntity();
        detailOrder.setOrderId(orderId);
        for (ItemResponseModel item : listProduct) {
            detailOrder.setProductId(item.getProductId());
            detailOrder.setQuantity(item.getQuantity());
            detailOrder.setPrice(item.getPrice());
            detailOrderRepository.save(detailOrder);
        }

    }
    private int createPayment(int orderId, float amount, Date payDate){
        PaymentEntity payment = new PaymentEntity();
        payment.setOrderId(orderId);
        payment.setPayAmount(amount);
        payment.setPayDate(payDate);
        paymentRepository.save(payment);
        return payment.getPayId();
    }
    private void createTransaction(int bankId,int payId, Date date){
        TransactionUserEntity transaction  = new TransactionUserEntity();
        transaction.setBankId(bankId);
        transaction.setPayId(payId);
        transaction.setTransDate(date);
        transactionUserRepository.save(transaction);
    }

}
