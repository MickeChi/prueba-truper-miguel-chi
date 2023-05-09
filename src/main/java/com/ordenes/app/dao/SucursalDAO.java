package com.ordenes.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ordenes.app.entity.Sucursal;

@Repository
public interface SucursalDAO extends JpaRepository<Sucursal, Long>{

}
