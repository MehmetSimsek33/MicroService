package com.kodlamaio.inventorySerivece.dataAccess.mongoDb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.inventorySerivece.entities.mongo.CarMongoDetail;

public interface CarMongoRepository extends MongoRepository<CarMongoDetail, String> {

}
