package com.example.hotel_management_project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_management_project.entity.CustomerDetailsEntity;
import com.example.hotel_management_project.entity.CustomerLogsEntity;
import com.example.hotel_management_project.entity.PaymentDetailsEntity;
import com.example.hotel_management_project.entity.RoomDetailsEntity;
import com.example.hotel_management_project.enums.PaymentStatus;
import com.example.hotel_management_project.enums.RoomStatus;
import com.example.hotel_management_project.repositoryPl.CustomerLogRepository;
import com.example.hotel_management_project.repositoryPl.CustomerRepository;
import com.example.hotel_management_project.repositoryPl.PaymentRepository;
import com.example.hotel_management_project.repositoryPl.RoomRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerLogsService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private CustomerLogRepository customerLogRepository;	

    public CustomerLogsEntity saveCustomerLogs(CustomerDetailsEntity custDetails, RoomDetailsEntity roomDetails, PaymentDetailsEntity paymentDetails) {
        CustomerLogsEntity log = new CustomerLogsEntity();
        log.setCustomerDetailsEntity(custDetails);
        log.setRoomDetailsEntity(roomDetails);
        log.setPaymentDetailsEntity(paymentDetails);	

        return customerLogRepository.save(log);
    }
	
    @Transactional
	public List<CustomerLogsEntity> getAllCustomerLogs() {
		return customerLogRepository.findAll();
	}
    
    
    
    public String logCustomerBooking(CustomerLogsEntity customerLog) {
        // Validate Customer
        CustomerDetailsEntity customer = customerRepository.findById(customerLog.getCustomerDetailsEntity().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Validate Room
        RoomDetailsEntity room = roomRepository.findById(customerLog.getRoomDetailsEntity().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getRoomStatus() != RoomStatus.AVAILABLE) {
            throw new RuntimeException("Room is not available");
        }

        // Calculate Payment
        Double totalPrice = room.getPrice() * customerLog.getPaymentDetailsEntity().getStayDays();

        // Save Payment Details
        PaymentDetailsEntity payment = new PaymentDetailsEntity();
        payment.setStayDays(customerLog.getPaymentDetailsEntity().getStayDays());
        payment.setTotalPrice(totalPrice);
        payment.setPaymentMethod(customerLog.getPaymentDetailsEntity().getPaymentMethod());
        payment.setPaymentStatus(PaymentStatus.PROCESSING); // Assuming payment verification later

        payment = paymentRepository.save(payment);

        // Update Room Status
        room.setRoomStatus(RoomStatus.BOOKED);
        roomRepository.save(room);

        // Save Customer Log
        customerLog.setCustomerDetailsEntity(customer);
        customerLog.setRoomDetailsEntity(room);
        customerLog.setPaymentDetailsEntity(payment);
        customerLog.setLogTimestamp(LocalDateTime.now());

        customerLogRepository.save(customerLog);

        return "Booking logged successfully! Booking ID: " + customerLog.getId();
    }

}
