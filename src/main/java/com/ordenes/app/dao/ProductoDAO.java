package com.ordenes.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ordenes.app.entity.Producto;

@Repository
public interface ProductoDAO extends JpaRepository<Producto, Long>{

}
