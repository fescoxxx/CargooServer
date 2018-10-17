package com.passing_parcel.api.server.repository.userRepository;

import com.passing_parcel.api.server.entity.entityUser.unregisteredUser.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, String> {
}
