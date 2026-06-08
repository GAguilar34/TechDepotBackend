package com.techdepot.backend.payment.service;

import com.techdepot.backend.payment.model.Method;
import com.techdepot.backend.payment.model.Payment;
import com.techdepot.backend.payment.model.Status;
import com.techdepot.backend.payment.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public boolean validateDate(String expirationDate) {
        if (expirationDate == null || !expirationDate.matches("\\d{2}/\\d{2}")) {
            return false;
        }

        String[] parts = expirationDate.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]) + 2000;

        //Validar que los meses esten entre 1 y 12
        if (month < 1 || month > 12) {
            return false;
        }

        // Comparar con la fecha actual
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        if (year < currentYear) {
            return false;
        }

        if (year == currentYear && month < currentMonth) {
            return false;
        }

        return true;
    }

    public void generatePayment(String nameOwner, String expirationDate, String lastFourDigits, double amount) {

        if (nameOwner == null || nameOwner.trim().isEmpty()) {
            throw new RuntimeException("El nombre del propietario es requerido.");
        }

        if (amount <= 0) {
            throw new RuntimeException("El monto debe ser mayor a 0.");
        }

        if (lastFourDigits == null || lastFourDigits.length() != 4) {
            throw new RuntimeException("Los digitos de la tarjeta no son validos.");
        }

        if (!validateDate(expirationDate)) {
            throw new RuntimeException("Su tarjeta ya esta vencida.");
        }

        Payment payment = new Payment();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setMethod(Method.TARJETA_CREDITO);
        payment.setStatus(Status.APROBADO);
        payment.setPaidAt(LocalDateTime.now());
        payment.setAmount(amount);
        payment.setNameOwner(nameOwner.trim());
        payment.setLastFourDigits(lastFourDigits);
        payment.setExpirationDate(expirationDate);
        
        paymentRepository.save(payment);
    }
}
