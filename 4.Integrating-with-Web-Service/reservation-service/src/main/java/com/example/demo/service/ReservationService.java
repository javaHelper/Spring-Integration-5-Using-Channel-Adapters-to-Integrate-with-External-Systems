package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Reservation;

public interface ReservationService {
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    Reservation save(Reservation widget);
    void deleteById(Long id);
}
