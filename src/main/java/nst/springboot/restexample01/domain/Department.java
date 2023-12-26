/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Ime je obavezno polje")
    @Size(min = 2, max = 10, message = "Broj znakova je od 2 do 10")
    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Member supervisor;

    @OneToOne
    @JoinColumn(name = "secretary_id")
    private Member secretary;

    @OneToMany(mappedBy = "department")
    private Set<Member> members;
}
