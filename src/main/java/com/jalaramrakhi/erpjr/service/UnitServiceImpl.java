package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.UnitMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.UnitNotFoundException;
import com.jalaramrakhi.erpjr.entity.Unit;
import com.jalaramrakhi.erpjr.repository.UnitRepository;
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
public class UnitServiceImpl implements UnitService{

    private UnitRepository unitRepository;

    @Autowired
    public UnitServiceImpl(UnitRepository unitRepository) {
        Assert.notNull(unitRepository, "UnitRepository must not be null!");
        this.unitRepository = unitRepository;
    }

    @Override
    public ResponseEntity<List<Unit>> getAllUnits() {
        List<Unit> allUnits = unitRepository.findAll();

        return new ResponseEntity<List<Unit>>(allUnits, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Unit> getSingleUnit(Integer id) {
        Unit getUnit = findUnitIfExists(id);
        return new ResponseEntity<Unit>(getUnit, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Unit> addNewUnit(Unit unit, HttpServletRequest request) {
        if(null != unit.getUnit_type() && unit.getUnit_type().length() > 0) {
            unitRepository.saveAndFlush(unit);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", unitUrlHelper(unit, request));
            return new ResponseEntity<Unit>(unit, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new UnitMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Unit> updateUnit(Integer id, Unit unit) {
        Unit existingUnit = findUnitIfExists(id);

        if(null != unit.getUnit_type() && unit.getUnit_type().length() > 0) {
            BeanUtils.copyProperties(unit, existingUnit);

            // Ensure ID remains unchanged
            existingUnit.setUnit_id(id);

            Unit updatedUnit = unitRepository.saveAndFlush(existingUnit);
            return new ResponseEntity<Unit>(updatedUnit, HttpStatus.OK);
        } else {
            throw new UnitMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Unit> deleteUnit(Integer id) {
        Unit existingUnit = findUnitIfExists(id);
        unitRepository.delete(existingUnit);
        return new ResponseEntity<Unit>(HttpStatus.NO_CONTENT);
    }

    private String unitUrlHelper(Unit unit, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(unit.getUnit_id());

        return resourcePath.toString();
    }

    private Unit findUnitIfExists(Integer id) {
        Optional<Unit> unit = unitRepository.findById(id);

        if(unit.isPresent()){
            return unit.get();
        }
        else {
            throw new UnitNotFoundException();
        }
    }
}
