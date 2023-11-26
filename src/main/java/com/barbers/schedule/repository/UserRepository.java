package com.barbers.schedule.repository;

import com.barbers.schedule.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
     UserDetails findByLogin(String login);
}
