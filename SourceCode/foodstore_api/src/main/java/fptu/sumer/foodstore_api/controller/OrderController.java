package fptu.sumer.foodstore_api.controller;


import fptu.sumer.foodstore_api.entity.*;
import fptu.sumer.foodstore_api.reponsitory.*;
import fptu.sumer.foodstore_api.responsemodel.ItemModel;
import fptu.sumer.foodstore_api.responsemodel.ItemRequestModel;
import io.swagger.annotations.Api;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Order Management")
public class OrderController {

    private Logger logger = Logger.getLogger(this.getClass());

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


    @GetMapping("orders")
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
    @PostMapping("orders")
    public ResponseEntity updateOrderById(
            @RequestBody ItemRequestModel item
    ) {

        String userId = item.getUserId();
        float total = item.getTotal();
        String notes = item.getNotes();
        List<ItemModel> listProduct = item.getListProduct();

        BankAccountEntity bankAccount = getAccountByUserId(userId);
        if (bankAccount != null) {
            // chack tai khoan con tien hay k
            float sodu = bankAccount.getAccMoney();
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

            //get id order
            int orderId = order.getOrderId();

            //create order detail
            createNewDetailOrder(orderId, listProduct);
            ///create payment and get payid
            int payId = createPayment(orderId, total, orderDate);
            // create transaction
            createTransaction(bankid, payId, orderDate);

            return new ResponseEntity(HttpStatus.OK);

        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("order/{id}")
    public ResponseEntity createNewOrder(@PathVariable int id, @RequestBody OrderListEntity order) {

        return null;

    }


    private BankAccountEntity getAccountByUserId(String userId) {
        BankAccountEntity bankAccount = new BankAccountEntity();
        bankAccount = bankAccountRepository.findByUserIdAndIsActive(userId, 1);
        return bankAccount;
    }

    private void createNewDetailOrder(int orderId, List<ItemModel> listProduct) {
        DetailOrderEntity detailOrder = new DetailOrderEntity();
        detailOrder.setOrderId(orderId);
        for (ItemModel item : listProduct) {
            detailOrder.setProductId(item.getProductId());
            detailOrder.setQuantity(item.getQuantity());
            detailOrder.setPrice(item.getPrice());
            detailOrderRepository.save(detailOrder);
        }

    }

    private int createPayment(int orderId, float amount, Date payDate) {
        PaymentEntity payment = new PaymentEntity();
        payment.setOrderId(orderId);
        payment.setPayAmount(amount);
        payment.setPayDate(payDate);
        paymentRepository.save(payment);
        return payment.getPayId();
    }

    private void createTransaction(int bankId, int payId, Date date) {
        TransactionUserEntity transaction = new TransactionUserEntity();
        transaction.setBankId(bankId);
        transaction.setPayId(payId);
        transaction.setTransDate(date);
        transactionUserRepository.save(transaction);
    }

}
