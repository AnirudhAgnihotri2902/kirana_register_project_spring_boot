package org.example.kirana_register_project.crud.repository;

import org.example.kirana_register_project.crud.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Users findById(UUID id);

    void deleteById(UUID userId);
}
