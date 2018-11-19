package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.AddressMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.AddressNotFoundException;
import com.jalaramrakhi.erpjr.entity.Address;
import com.jalaramrakhi.erpjr.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        Assert.notNull(addressRepository, "AddressRepository must not be null!");
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<List<Address>> getAllAddresss() {
        List<Address> allAddresss = addressRepository.findAll();

        return new ResponseEntity<List<Address>>(allAddresss, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Address> getSingleAddress(Integer id) {
        Address getAddress = findAddressIfExists(id);
        return new ResponseEntity<Address>(getAddress, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Address> addNewAddress(Address address, HttpServletRequest request) {
        if(null != address.getAddress_line() && address.getAddress_line().length() > 0) {
            addressRepository.saveAndFlush(address);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", addressUrlHelper(address, request));
            return new ResponseEntity<Address>(address, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new AddressMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Address> updateAddress(Integer id, Address address) {
        Address existingAddress = findAddressIfExists(id);

        if(null != address.getAddress_line() && address.getAddress_line().length() > 0) {
            BeanUtils.copyProperties(address, existingAddress);

            // Ensure ID remains unchanged
            existingAddress.setAddress_id(id);

            Address updatedAddress = addressRepository.saveAndFlush(existingAddress);
            return new ResponseEntity<Address>(updatedAddress, HttpStatus.OK);
        } else {
            throw new AddressMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Address> deleteAddress(Integer id) {
        Address existingAddress = findAddressIfExists(id);
        addressRepository.delete(existingAddress);
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

    private String addressUrlHelper(Address address, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(address.getAddress_id());

        return resourcePath.toString();
    }

    private Address findAddressIfExists(Integer id) {
        Optional<Address> address = addressRepository.findById(id);

        if(address.isPresent()){
            return address.get();
        }
        else {
            throw new AddressNotFoundException();
        }
    }
}
