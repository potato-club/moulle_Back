package com.gamza.moulle.repository;

import com.gamza.moulle.entity.ShelfEntity;
import com.gamza.moulle.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelfRepository extends JpaRepository<ShelfEntity, Long> {
    List<ShelfEntity> findByUserEntity(UserEntity userEntity);
    ShelfEntity findByUserEntityAndShelfName(UserEntity userEntity, String shelfName);
    ShelfEntity findByIdAndUserEntity(Long id, UserEntity userEntity);
}
