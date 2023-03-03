package com.komfortcieplny.FakeEmailSender.user.repository;

import com.komfortcieplny.FakeEmailSender.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
