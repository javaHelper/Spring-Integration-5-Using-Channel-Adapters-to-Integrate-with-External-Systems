package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "reservations")
public class Reservation {
    private ObjectId id;
    private String name;
    private String status;
    
    public Reservation(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
