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

import com.example.hotel_management_project.dto.CustomerDetails;
import com.example.hotel_management_project.entity.CustomerDetailsEntity;
import com.example.hotel_management_project.service.CustomerDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer Details")
@RestController
@RequestMapping("/customer")
public class CustomerDetailsResource {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerDetailsResource.class);
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	

	@GetMapping("/check")
	public String getString() {
		return "Customer Resource";
	}
	
	
    @Operation(
            summary = "Retrieves CustomerDetails By Id",
            description = "Fetches customer details using the given ID. Returns a single customer record if found."
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer details retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@GetMapping("/details/{id}")
	public Optional<CustomerDetailsEntity> findDetailsById(@PathVariable Long id) {
    	logger.info("INFO:customer details by id are fectched successfully");
		return customerDetailsService.getCustomerDetailsById(id);
	}
	
    @Operation(
    		summary = "Retreives the customer details with CustomerID ",
    		description = "Fetches the the cutomer details using unique reference customerID if found")
    @GetMapping("/detail/{customerID}")
    public Optional<CustomerDetailsEntity> getDetailsByCustomerID(@PathVariable String customerID){
    	 logger.info("INFO:customer details by cutomer id are fetched successfully");
    	return customerDetailsService.getByCustomerID(customerID);
    }
    
    @Operation(
    		summary = "Retreives CustomerDetails By CustomerName",
    		description = "Fetches customer's details using the particular customer name if found")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Customer details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	@GetMapping("/{customerName}")
	public List<CustomerDetailsEntity> findDetailsByCustomerName(@PathVariable String customerName) {
    	logger.info("INFO:customer's details are retrieved by customer name successfully");
		return customerDetailsService.getCustomersByName(customerName);
	}
	
    @Operation(
    		summary = "Retreives All CustomerDetails",
    		description = "Fetches all customer's details registered in the application")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Customer details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	@GetMapping
	public ResponseEntity<List<CustomerDetailsEntity>> findAllDetails() {
		List<CustomerDetailsEntity> customers = customerDetailsService.getAllCustomers();
		logger.info("INFO:successfully retrieved all customers details exist in database");
		return ResponseEntity.ok(customers);
	}

    
    @Operation(
    		summary = "Persist The CustomerDetails",
    		description = "Save the customer's details")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Customer details registered successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid Details supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	@PostMapping("/register")
	public ResponseEntity<CustomerDetailsEntity> register(@RequestBody CustomerDetails details) {
	    CustomerDetailsEntity savedEntity = customerDetailsService.saveDetails(details);
	    logger.info("INFO:customer details are saved successfully");
	    return ResponseEntity.status(200).body(savedEntity); 
	}
	
    
    @Operation(
    		summary = "Creates Token With The CustomerDetails",
    		description = "Verify and creates token with customer's details")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Customer details verified successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })   
	@PostMapping("/login")
	public String customerLogin(@RequestBody CustomerDetails details) {
    	logger.info("INFO:customername and password is verified and cretaed Bearer Token");
		return customerDetailsService.VerifyCustomer(details);
	}
	
    
    @Operation(
    		summary = "Update The CustomerDetails By customerId",
    		description = "Alter and update customer's details by CustomerId")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Customer details updated successfully"),
    	@ApiResponse(responseCode = "401", description = "Customer ID not found"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })  
	@PutMapping("/update/{customerid}")
	public ResponseEntity<CustomerDetailsEntity> upadteDetails(@PathVariable String customerID, @RequestBody CustomerDetails details){
		CustomerDetailsEntity updatedEntity = customerDetailsService.updateDetails(customerID, details);
		logger.info("INFO:successfully updated customer details");
		return ResponseEntity.ok(updatedEntity);
	}

    
    @Operation(
    		summary = "Delete The CustomerDetails of Customer By Id",
    		description = "Delete customer's details by Id if not required")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Customer details deleted successfully"),
    	@ApiResponse(responseCode = "401", description = "Customer ID not found"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })  
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerDetailsEntity> deleteById(@PathVariable Long id){
		customerDetailsService.deleteCustomer(id);
		logger.info("successfully deleted customer by id");
		return ResponseEntity.noContent().build();
	}
	
//	@PostMapping("/send-otp")
//	public ResponseEntity<String> sendOtp(@PathParam(value = "mobileNUmber") String mobileNumber){
//		String response = customerDetailsService.sendOtp(mobileNumber);
//		return ResponseEntity.ok(response);
//	}
//	
//	@PostMapping("/resetPassword")
//    public ResponseEntity<String> resetPassword(
//            @RequestParam String mobileNumber,
//            @RequestParam String otp,
//            @RequestParam String newPassword) {
//        String response = customerDetailsService.resetPassword(mobileNumber, otp, newPassword);
//        return ResponseEntity.ok(response);
//	}
    
}
