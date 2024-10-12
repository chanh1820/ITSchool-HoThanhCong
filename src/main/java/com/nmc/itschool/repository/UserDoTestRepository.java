package com.nmc.itschool.repository;

import com.nmc.itschool.entity.UserDoTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDoTestRepository extends JpaRepository<UserDoTestEntity, Long>{

    @Query(value = "SELECT * FROM user_do_test_tbl " +
            "WHERE slug=:slug AND user_name=:userName AND delete_flag = false ORDER BY user_do_test_id ASC", nativeQuery = true)
    Optional<List<UserDoTestEntity>> findBySlugAndUserName(String userName, String slug);

}
