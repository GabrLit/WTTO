package com.example.NFTMarketplace.repositiories;
import com.example.NFTMarketplace.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getFirstByNameAndSurname(String name, String surname);
    Optional<User> getUserByName(String name);
    User getUserById(Long id);
}
