package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.Address;
import com.jalaramrakhi.erpjr.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/address")
@RestController
public class AddressController {
    
    @Autowired
    private AddressService addressService;

    // List All Address
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Address>> getAllAddress() throws Throwable {
        return addressService.getAllAddresss();
    }

    // Get One Address
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Address> getSingleAddress(@PathVariable Long id) {
        return addressService.getSingleAddress(id);
    }

    // Add New Address
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Address> createNewAddress(@RequestBody Address address, HttpServletRequest req) {
        return addressService.addNewAddress(address, req);
    }

    // Update Address with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    // Delete Address
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id);
    }
}
