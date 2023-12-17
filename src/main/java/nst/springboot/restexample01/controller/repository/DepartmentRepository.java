/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller.repository;

import java.util.Optional;
import nst.springboot.restexample01.controller.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author student2
 */
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    //vrati depratment na osnovu imena
    Optional<Department> findByName(String name);
}
