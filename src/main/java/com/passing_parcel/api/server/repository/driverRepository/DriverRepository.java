package com.passing_parcel.api.server.repository.driverRepository;

import com.passing_parcel.api.server.entity.entityDriver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
