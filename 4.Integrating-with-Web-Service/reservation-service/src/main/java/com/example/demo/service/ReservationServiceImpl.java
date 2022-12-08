package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.google.common.collect.Lists;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Reservation> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Reservation save(Reservation widget) {
        // Increment the version number
        widget.setVersion(widget.getVersion()+1);

        // Save the widget to the repository
        return repository.save(widget);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
