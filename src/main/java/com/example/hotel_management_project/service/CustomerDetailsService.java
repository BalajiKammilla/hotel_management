package com.example.hotel_management_project.service;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.hotel_management_project.dto.CustomerDetails;
import com.example.hotel_management_project.entity.CustomerDetailsEntity;
import com.example.hotel_management_project.enums.MaritalStatus;
import com.example.hotel_management_project.exception.UserNotFoundException;
import com.example.hotel_management_project.exception.ValidationException;
import com.example.hotel_management_project.repositoryPl.CustomerRepository;
import com.example.hotel_management_project.securityConfig.JWTService;
//import com.google.i18n.phonenumbers.PhoneNumberUtil;
//import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import jakarta.transaction.Transactional;

@Service
public class CustomerDetailsService {
	
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerDetailsService.class);
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private Map<String, String> otpStore = new HashMap<String, String>();
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	
	 public Optional<CustomerDetailsEntity> getCustomerDetailsById(Long id) {
		if(id == null || id <= 0) {
			throw new ValidationException("Invalid Id, ID must be positive number");
		}
		return customerRepository.findById(id);
	}
	 
	  
	 public Optional<CustomerDetailsEntity> getByCustomerID(String customerID){
		 if(customerID == null || customerID.isEmpty()) {
			 throw new RuntimeException("Invalid customerID please re-enter correct customerID");
		 }
		 return customerRepository.findByCustomerID(customerID);
	 }
	
	 
	@Transactional
	public List<CustomerDetailsEntity> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	
	public List<CustomerDetailsEntity> getCustomersByName(String customerName) {
		if(customerName == null || customerName.isEmpty()) {
			logger.error("ERROR:CustomerName cannot be null or empty");
			throw new ValidationException("CustomerName cannot be null or empty");
		}
		return customerRepository.getCustomerDetailsByCustomerName(customerName);
	}
	
	
	public CustomerDetailsEntity saveDetails(CustomerDetails customerDetails) {
		
		CustomerDetailsValidations(customerDetails);
//		PhoneNumberValidation(customerDetails);
		String custId = generateRegistrationID(customerDetails.getCustomerName());
		
        CustomerDetailsEntity entity = new CustomerDetailsEntity();
        entity.setCustomerName(customerDetails.getCustomerName());
        entity.setCustomerID(custId);
        entity.setAge(customerDetails.getAge());
        entity.setAddress(customerDetails.getAddress());
        entity.setCountryCode(customerDetails.getCountryCode() != null ? customerDetails.getCountryCode() : "+91");
        entity.setIdProof(customerDetails.getIdProof());
        entity.setMobileNumber(customerDetails.getMobileNumber());
        entity.setPassword(encoder.encode(customerDetails.getPassword()));
        logger.info("ENCODE: password in encoded successfully");
        entity.setMaritalStatus(customerDetails.getMaritalStatus() != null ? customerDetails.getMaritalStatus() : MaritalStatus.NOTDEFINED);
        
        return customerRepository.save(entity);
    }

	
/*	private void PhoneNumberValidation(CustomerDetails customerDetails) {
		try {
	        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
	        PhoneNumber phoneNumber = phoneNumberUtil.parse(customerDetails.getMobileNumber(), customerDetails.getCountryCode());
	        
	        if (!phoneNumberUtil.isValidNumber(phoneNumber)) {
	            throw new ValidationException("Invalid MobileNumber for the specified country");
	        }
	    } catch (Exception e) {
	        throw new ValidationException("Invalid MobileNumber format or country code");
	    }
	} */
	
	

	private void CustomerDetailsValidations(CustomerDetails customerDetails) {
		
		if(customerDetails.getCustomerName() == null || customerDetails.getCustomerName().isEmpty() && customerDetails.getCustomerName().length() >= 50) {
			throw new ValidationException("Customer Name cannot be null or empty and less than 50 charcters ");
		}
		if(!customerDetails.getCustomerName().matches("[A-Za-z\\s]+")) {
			throw new ValidationException("CustomerName can only contain alphabets and spaces");
		}
		logger.info("customer name is validated !");
		if(customerDetails.getAddress() == null || customerDetails.getAddress().isEmpty()) {
			throw new ValidationException("Address cannot be null or empty");
		}
		if(customerDetails.getAddress().length() > 255) {
			throw new ValidationException("Address cannot exceed 255 charcters");
		}
		logger.info("customer address is validated !");
		if(customerDetails.getAge() <= 0) {
			throw new ValidationException("Age must be greater than 0");
		}
		logger.info("customer age is validated !");
		if(customerDetails.getIdProof() == null || customerDetails.getIdProof().isEmpty()) {
			throw new ValidationException("IdProof cannot be null or empty");
		}
		logger.info("customer identity is validated !");
		if(customerDetails.getMobileNumber().equals("+91") && customerDetails.getMobileNumber() == null || !customerDetails.getMobileNumber().matches("\\d{10}")) {
			throw new ValidationException("Invalid MobileNumber, Indian mobileNumbers should conatain 10 digits");
		}
		if(customerRepository.existsByMobileNumber(customerDetails.getMobileNumber())) {
			logger.error("ERROR:mobile number is exists");
			throw new ValidationException("Mobile number already exists");
		}
		logger.info("customer mobile number is validated !");
	}
	
	
	public CustomerDetailsEntity updateDetails(String customerId, CustomerDetails custDetails) {
		
	      CustomerDetailsEntity existingEntity = customerRepository.findByCustomerID(customerId)
	              .orElseThrow(() -> new RuntimeException("Customer not found with CustomerID: " + customerId));
	      
	      existingEntity.setCustomerName(custDetails.getCustomerName());
	      existingEntity.setAddress(custDetails.getAddress());
	      existingEntity.setCountryCode(custDetails.getCountryCode());
	      existingEntity.setAge(custDetails.getAge());
	      existingEntity.setIdProof(custDetails.getIdProof());
	      existingEntity.setMobileNumber(custDetails.getMobileNumber());
	      
	      logger.info("INFO:Customer details are updated by customerID");
	      return customerRepository.save(existingEntity);
	}
	
	
	public void deleteCustomer(Long id) {
		if(!customerRepository.existsById(id)) {
			throw new UserNotFoundException("Customer not found with id"+id);
		}
		customerRepository.deleteById(id);
	}

	
	public String VerifyCustomer(CustomerDetails details) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(details.getCustomerName(),details.getPassword()));
		if(authentication.isAuthenticated()) {
			logger.info("customer details are authenticated successfully");
			return jwtService.generateToken(details.getCustomerName());
		}
		logger.error("ERROR:customer details are invalid");
		return "failed verification";
	}
	
//	<!--To Generate and verify Otp to change password in case of forget password -->
	
	/*public String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(90000);
		return String.valueOf(otp);
	}
	
	public String sendOtp(String mobileNumber) {
		Optional<CustomerDetailsEntity> customer = customerRepository.findByMobileNumber(mobileNumber);
		if(customer.isEmpty()) {
			throw new RuntimeException("Mobile Number Not Found");
		}
		
		String otp = generateOtp();
		otpStore.put(mobileNumber, otp);
		System.out.println("OTP sent to "+ mobileNumber +":"+ otp);
		return "OTP sent Successfully";
	}
	
	
	public String resetPassword(String mobileNumber, String otp, String newPassword) {
	
		if(!otpStore.containsKey(mobileNumber) || !otpStore.get(mobileNumber).equals(otp)) {
			throw new RuntimeException("Invalid OTP !");
		}
		
		CustomerDetailsEntity customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new RuntimeException("Mobile Number Not Found"));
		
		customer.setPassword(encoder.encode(newPassword));
		customerRepository.save(customer);
		
		otpStore.remove(mobileNumber);
		
		return "Password reset Successfully !";
	}*/
	
	
	private String generateRegistrationID(String customerName) {
		if(customerName == null || customerName.trim().isEmpty()) {
			throw new IllegalArgumentException("CustomerName should not be null or empty");
		}
		
		String year = String.valueOf(Year.now().getValue()).substring(2);
		String firstLetterOfCustomerName = customerName.trim().substring(0, 1).toUpperCase();
		String prefix = year + firstLetterOfCustomerName;
		
		Optional<CustomerDetailsEntity> lastCustomer = customerRepository.findTopByCustomerIDStartingWithOrderByCustomerIDDesc(prefix);
		logger.info("fetched last customer who used the prefix");
		int sequenceNumber = 101;
		
		if(lastCustomer.isPresent()) {
			String lastCustId = lastCustomer.get().getCustomerID();
			String lastDigitStr = lastCustId.substring(prefix.length());
			
			try {
				sequenceNumber = Integer.parseInt(lastDigitStr) + 1;
			} catch(NumberFormatException e) {
				throw new RuntimeException("Invalid CustomerId Format"+ lastCustId);
			}
		}
		logger.info("CustomerID generated and returned");
		return prefix + sequenceNumber;
	}


	public Map<String, String> getOtpStore() {
		return otpStore;
	}


	public void setOtpStore(Map<String, String> otpStore) {
		this.otpStore = otpStore;
	}
}
