package mk.ukim.finki.wp.library.service;

import mk.ukim.finki.wp.library.model.dto.LoginUserRequestDto;
import mk.ukim.finki.wp.library.model.dto.LoginUserResponseDto;
import mk.ukim.finki.wp.library.model.dto.RegisterUserRequestDto;
import mk.ukim.finki.wp.library.model.dto.RegisterUserResponseDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);
}

