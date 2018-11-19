package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.Unit;
import com.jalaramrakhi.erpjr.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
@RequestMapping(value = "/unit")
@RestController
public class UnitController {
    
    @Autowired
    private UnitService unitService;

    // List All Units
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Unit>> getAllUnits() throws Throwable {
        return unitService.getAllUnits();
    }

    // Get One Unit
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Unit> getSingleUnit(@PathVariable Integer id) {
        return unitService.getSingleUnit(id);
    }

    // Add New Unit
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Unit> createNewUnit(@RequestBody Unit unit, HttpServletRequest req) {
        return unitService.addNewUnit(unit, req);
    }

    // Update Unit with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Unit> updateUnit(@PathVariable Integer id, @RequestBody Unit unit) {
        return unitService.updateUnit(id, unit);
    }

    // Delete Unit
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Unit> deleteUnit(@PathVariable Integer id) {
        return unitService.deleteUnit(id);
    }
}
