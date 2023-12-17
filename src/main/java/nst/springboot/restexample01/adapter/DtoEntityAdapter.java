/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.adapter;

/**
 *
 * @author student2
 */
public interface DtoEntityAdapter<T, E> {
    T toDto(E e);
    E toEntity(T t);
}
