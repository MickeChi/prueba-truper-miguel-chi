package com.ordenes.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ordenes.app.entity.Orden;

@Repository
public interface OrdenDAO extends JpaRepository<Orden, Long>{

}
