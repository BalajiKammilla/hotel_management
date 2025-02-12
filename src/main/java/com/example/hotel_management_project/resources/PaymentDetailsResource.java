package com.example.hotel_management_project.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel_management_project.dto.PaymentDetails;
import com.example.hotel_management_project.entity.PaymentDetailsEntity;
import com.example.hotel_management_project.service.PaymentDetailsService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Payment Details")
@RestController
@RequestMapping("/api/payment")
public class PaymentDetailsResource {
	
	@Autowired
	private PaymentDetailsService paymentDetailsService;
	
	@GetMapping("/check")
	public String getString() {
		return "Payment Resource";
	}
	
	@GetMapping("/details/{id}")
	public Optional<PaymentDetailsEntity> getpaymentDetialsById(@PathVariable Long id) {
		return paymentDetailsService.getPaymentDetailsById(id);
	}
	
	@GetMapping
	public ResponseEntity<List<PaymentDetailsEntity>> allPaymentDetails() {
		List<PaymentDetailsEntity> list = paymentDetailsService.getAllPaymentDetails();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/details/{paymentMethod}")
	public List<PaymentDetailsEntity> findByPaymentMethod(@PathVariable String paymentMethod){
		return paymentDetailsService.findPaymentDetailsByPaymentMethod(paymentMethod);
	}
	
	@PostMapping("/save")
	public ResponseEntity<PaymentDetailsEntity> saveDetails(@RequestBody PaymentDetails payDetails){
		PaymentDetailsEntity saveEntity = paymentDetailsService.saveDetails(payDetails);
		return ResponseEntity.status(200).body(saveEntity);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PaymentDetailsEntity> updateDetails(@PathVariable Long id, @RequestBody PaymentDetails details){
		
		PaymentDetailsEntity updateEntity = paymentDetailsService.updateDetails(id, details);
		return ResponseEntity.ok(updateEntity);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PaymentDetailsEntity> deleteBydetail(@PathVariable Long id){
		paymentDetailsService.deleteByPayment(id);
		return ResponseEntity.noContent().build();
	}

}
