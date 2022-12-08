package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
