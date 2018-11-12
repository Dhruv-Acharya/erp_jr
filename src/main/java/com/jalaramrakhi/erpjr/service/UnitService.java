package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.Unit;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UnitService {
    public ResponseEntity<List<Unit>> getAllUnits();
    public ResponseEntity<Unit> getSingleUnit(Integer id);
    public ResponseEntity<Unit> addNewUnit(Unit unit, HttpServletRequest request);
    public ResponseEntity<Unit> updateUnit(Integer id, Unit unit);
    public ResponseEntity<Unit> deleteUnit(Integer id);
}
