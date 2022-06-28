package com.example.NFTMarketplace.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    private Long id;
    private String name;
    private String surname;
    private String nickname;
}
