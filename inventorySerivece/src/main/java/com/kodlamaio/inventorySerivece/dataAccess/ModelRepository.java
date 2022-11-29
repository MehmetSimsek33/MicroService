package com.kodlamaio.inventorySerivece.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventorySerivece.entities.Model;

public interface ModelRepository  extends JpaRepository<Model, String> {

}