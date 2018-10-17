package com.passing_parcel.api.server.repository.userRepository;

import com.passing_parcel.api.server.entity.entityUser.registeredUser.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
