package net.ensah.minik8s.service;


import net.ensah.minik8s.dao.IUserDao;
import net.ensah.minik8s.dtos.UserRequestDto;
import net.ensah.minik8s.dtos.UserResponseDto;
import net.ensah.minik8s.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final IUserDao dao;


    public UserService(IUserDao dao) {
        this.dao = dao;

    }

   @Transactional
    public UserResponseDto addUser(UserRequestDto requestDto) {
        UserEntity saved = dao.save(UserEntity.builder()
                .firstName(requestDto.firstName())
                .lastName(requestDto.lastName())
                .email(requestDto.email())
                .password(requestDto.password())
                .build());
        return new  UserResponseDto(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getEmail());
    }

   public List<UserResponseDto> getAll(){
        return dao.findAll().stream().map(
                u->new UserResponseDto(u.getId(),u.getFirstName(),u.getLastName(),u.getEmail())
        ).toList();
    }

    public UserResponseDto getByEmail(String email){
        UserEntity userEntity = dao.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Email doesn't exists"));
        return new  UserResponseDto(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail());
    }
}
