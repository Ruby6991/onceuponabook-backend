package com.apiit.onceuponabook.repositories;

import com.apiit.onceuponabook.models.Login;
import com.apiit.onceuponabook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Integer> {
    boolean existsByUserAndPassword(User user,String password);
}
