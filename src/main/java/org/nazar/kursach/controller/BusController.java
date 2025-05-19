package org.nazar.kursach.controller;

import org.nazar.kursach.entity.Bus;
import org.nazar.kursach.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin
public class BusController {
    @Autowired
    private BusService service;

    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return service.addBus(bus);
    }

    @GetMapping("/depot")
    public List<Bus> getDepot(@RequestParam(defaultValue = "bus") String sortBy) {
        return service.getBuses("depot", sortBy);
    }

    @GetMapping("/route")
    public List<Bus> getRoute(@RequestParam(defaultValue = "bus") String sortBy) {
        return service.getBuses("route", sortBy);
    }

    @PutMapping("/{busNumber}/move")
    public ResponseEntity<?> moveBus(@PathVariable int busNumber, @RequestParam String to) {
        return service.moveBus(busNumber, to) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
