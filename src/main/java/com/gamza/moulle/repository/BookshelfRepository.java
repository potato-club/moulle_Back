package com.gamza.moulle.repository;

import com.gamza.moulle.entity.BookshelfEntity;
import com.gamza.moulle.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookshelfRepository extends JpaRepository<BookshelfEntity, Long> {
    List<BookshelfEntity> findByUserEntity(UserEntity userEntity);
}
