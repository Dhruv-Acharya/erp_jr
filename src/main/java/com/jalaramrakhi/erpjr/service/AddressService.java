package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.Address;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AddressService {
    public ResponseEntity<List<Address>> getAllAddresss();
    public ResponseEntity<Address> getSingleAddress(Integer id);
    public ResponseEntity<Address> addNewAddress(Address address, HttpServletRequest request);
    public ResponseEntity<Address> updateAddress(Integer id, Address address);
    public ResponseEntity<Address> deleteAddress(Integer id);
}
