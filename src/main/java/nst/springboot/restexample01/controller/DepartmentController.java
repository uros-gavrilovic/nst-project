/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;

import java.util.List;

import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.audit.DepartmentAudit;
import nst.springboot.restexample01.domain.network.NetworkPackage;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.service.DepartmentService;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import nst.springboot.restexample01.exception.MyErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author student2
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //dodaj novi department
    @PostMapping
    public ResponseEntity<DepartmentDto> save(@Valid @RequestBody DepartmentDto departmentDto) throws Exception {
        //ResponseEntity
        DepartmentDto deptDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}/set-supervisor")
//    public ResponseEntity<DepartmentDto> setSupervisor(@PathVariable("id") Long id, @RequestBody MemberDto memberDto)
//            throws Exception {
//        DepartmentDto deptDto = departmentService.updateSupervisor(id, memberDto);
//        return new ResponseEntity<>(deptDto, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}/set-secretary")
//    public ResponseEntity<DepartmentDto> setSecretary(@PathVariable("id") Long id, @RequestBody MemberDto memberDto)
//            throws Exception {
//        DepartmentDto deptDto = departmentService.updateSecretary(id, memberDto);
//        return new ResponseEntity<>(deptDto, HttpStatus.OK);
//    }

    @PutMapping("/{id}/promote-member")
    public ResponseEntity<DepartmentDto> promote(@PathVariable("id") Long departmentId,
                                                 @RequestParam("position") String position,
                                                 @RequestBody NetworkPackage<MemberDto> networkPackage) throws Exception {
        DepartmentDto deptDto = departmentService.promote(departmentId, position, networkPackage);
        return new ResponseEntity<>(deptDto, HttpStatus.OK);
    }

    @GetMapping("{id}/history")
    public ResponseEntity<List<DepartmentAudit>> getHistory(@PathVariable("id") Long id,
                                                            @RequestParam(required = true, name = "field") String field)
            throws Exception {
        List<DepartmentAudit> departmentAudits = departmentService.getHistory(id, field);
        return new ResponseEntity<>(departmentAudits, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public DepartmentDto findById(@PathVariable("id") Long id) throws Exception {
        return departmentService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public Department queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        throw new Exception("Nije implementirana.");
    }

    //azuriraj
    //obrisi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        /*
        try {
            departmentService.delete(id);
            return new ResponseEntity<>("Department removed!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(">>" + e.getMessage(), HttpStatus.NOT_FOUND);
        }*/

        departmentService.delete(id);
        return new ResponseEntity<>("Department removed!", HttpStatus.OK);

    }

    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> handleException(Exception e){
        System.out.println("nst.springboot.restexample01.controller.DepartmentController.handleException()");
        System.out.println("-----------pozvana metoda za obradu izuzetka u kontroleru -------------");
        
        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());
        
        return new ResponseEntity<>(myErrorDetails, HttpStatus.NOT_FOUND);

    }*/
    @ExceptionHandler(DepartmentAlreadyExistException.class)
    public ResponseEntity<MyErrorDetails> handleException(DepartmentAlreadyExistException e) {
        System.out.println("nst.springboot.restexample01.controller.DepartmentController.handleException()");
        System.out.println("-----------pozvana metoda za obradu izuzetka u kontroleru -------------");

        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());

        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);

    }
}
