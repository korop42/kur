package org.nazar.kursach.repository;

import org.nazar.kursach.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    List<Bus> findByStatusOrderByBusNumber(String status);
    List<Bus> findByStatusOrderByRouteNumber(String status);
}
