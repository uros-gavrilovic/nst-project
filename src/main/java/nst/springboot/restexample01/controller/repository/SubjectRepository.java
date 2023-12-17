/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author student2
 */
public interface SubjectRepository extends JpaRepository<Subject, Long>{
}
