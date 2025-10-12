package net.ensah.minik8s.dao;


import net.ensah.minik8s.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
