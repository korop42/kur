package org.nazar.kursach.service;


import org.nazar.kursach.entity.Bus;
import org.nazar.kursach.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    @Autowired
    private BusRepository repository;

    public Bus addBus(Bus bus) {
        bus.setStatus("depot");
        return repository.save(bus);
    }

    public List<Bus> getBuses(String status, String sortBy) {
        return sortBy.equals("route") ?
                repository.findByStatusOrderByRouteNumber(status) :
                repository.findByStatusOrderByBusNumber(status);
    }

    public boolean moveBus(int busNumber, String toStatus) {
        return repository.findById(busNumber).map(bus -> {
            bus.setStatus(toStatus);
            repository.save(bus);
            return true;
        }).orElse(false);
    }
}

