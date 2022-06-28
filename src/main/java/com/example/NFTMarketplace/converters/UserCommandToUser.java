package com.example.NFTMarketplace.converters;

import com.example.NFTMarketplace.commands.UserCommand;
import com.example.NFTMarketplace.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand source) {
        if (source == null) {
            return null;
        }

        final User user = new User();
        user.setName(source.getName());
        user.setSurname(source.getSurname());
        user.setNickname(source.getNickname());

        return user;
    }
}
