package com.example.procurement.features.payments.service;

import com.example.procurement.features.payments.dao.PaymentDao;
import com.example.procurement.features.payments.model.Payment;

import java.util.List;

public class PaymentService {

    private final PaymentDao paymentDao = new PaymentDao();

    public List<Payment> getAllPayments() {
        return paymentDao.findAll();
    }

    public Payment getPaymentById(int paymentId) {
        return paymentDao.findById(paymentId);
    }

    public void createPayment(Payment payment) {
        paymentDao.create(payment);
    }

    public boolean updatePayment(Payment payment) {
        return paymentDao.update(payment);
    }

    public boolean deletePayment(int paymentId) {
        return paymentDao.deleteById(paymentId);
    }
}
