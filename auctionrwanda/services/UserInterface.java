package com.auctionrwanda.services;

import com.auctionrwanda.DataTransferObject.UserRegistartionDto;
import com.auctionrwanda.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInterface extends UserDetailsService {
    public User saveAccount(UserRegistartionDto userRegistartionDto);
}
