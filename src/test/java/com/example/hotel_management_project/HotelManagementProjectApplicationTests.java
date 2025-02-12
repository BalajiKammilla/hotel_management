//package com.example.hotel_management_project;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.example.hotel_management_project.dto.CustomerDetails;
//import com.example.hotel_management_project.entity.CustomerDetailsEntity;
//import com.example.hotel_management_project.service.CustomerDetailsService;
//
//@SpringBootTest
//class HotelManagementProjectApplicationTests {
//
//	@Test
//	void contextLoads() {
//		assertThat(customerDetailsService).isNotNull();
//	}
//	
//    @Autowired
//    private CustomerDetailsService customerDetailsService;
//
//    @Test
//    void testSaveDetails_Success() {
//        // Prepare test data
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setCustomerName("John Roy");
//        customerDetails.setAge(30);
//        customerDetails.setAddress("123 Main Street");
//        customerDetails.setCountryCode("+91");
//        customerDetails.setMobileNumber("9876543210");
//        customerDetails.setIdProof("A123456789");
//        
//       
//        // Save details
//        CustomerDetailsEntity savedEntity = customerDetailsService.saveDetails(customerDetails);
//
//        // Assertions
//        assertThat(savedEntity).isNotNull();
//        assertThat(savedEntity.getId()).isNotNull();
//        assertThat(savedEntity.getCustomerName()).isEqualTo("John Roy");
//    }
//
//}