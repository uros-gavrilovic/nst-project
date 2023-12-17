/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.converter;

/**
 *
 * @author student2
 */
public interface DtoEntityConverter<T, E> {
    T toDto(E e);
    E toEntity(T t);
}
