package com.auctionrwanda.services.implementation;
import com.auctionrwanda.DataTransferObject.UserRegistartionDto;
import com.auctionrwanda.model.Role;
import com.auctionrwanda.model.User;
import com.auctionrwanda.repository.UserRepository;
import com.auctionrwanda.services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class userServiceImp implements UserInterface {

@Autowired
    private BCryptPasswordEncoder passwordEncoder;


    private final UserRepository userRepository;


    public userServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveAccount(UserRegistartionDto userRegistartionDto) {
        User user=new User(userRegistartionDto.getfName(),userRegistartionDto.getlName(), userRegistartionDto.getEmail(),
                passwordEncoder.encode(userRegistartionDto.getPassword())  , Arrays.asList(new Role("ROLE_ADMIN")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("invalid username or password");
        }else
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRoleToGrantedAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToGrantedAuthority(Collection<Role> roles){
     return    roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
}
