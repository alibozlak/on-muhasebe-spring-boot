package dev.bozlak.on_muhasebe_spring_boot.login.service;

import dev.bozlak.on_muhasebe_spring_boot.login.dtos.LoginRequestDto;

public interface LoginService {

    String login(LoginRequestDto loginRequestDto);
}
