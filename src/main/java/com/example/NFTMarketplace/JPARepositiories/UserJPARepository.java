package com.example.NFTMarketplace.JPARepositiories;

import com.example.NFTMarketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJPARepository extends JpaRepository<User, Long> {
    User getByNickname(String nickname);
}
