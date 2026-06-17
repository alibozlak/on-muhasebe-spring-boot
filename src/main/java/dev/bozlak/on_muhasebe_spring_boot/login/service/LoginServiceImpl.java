package dev.bozlak.on_muhasebe_spring_boot.login.service;

import dev.bozlak.on_muhasebe_spring_boot.admin.service.AdminService;
import dev.bozlak.on_muhasebe_spring_boot.login.dtos.LoginRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.security.JwtService;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;
import dev.bozlak.on_muhasebe_spring_boot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final AdminService adminService;

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequestDto.getPassword())
        );

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        UserIdAndIsAdminModel userIdAndIsAdminModel = this.userService.getModelForJwtTokenGenerated(username);
        Integer userId = userIdAndIsAdminModel.getUserId();;
        Short adminId = -1;
        if (userIdAndIsAdminModel.getIsAdmin())
            adminId = this.adminService.getAdminIdByUserId(userId);
        Map<String, Object> extraClaims = new HashMap<>(2);
        extraClaims.put("adminId", adminId);
        extraClaims.put("userId", userId);

        return this.jwtService.generateJwtToken(userDetails, extraClaims);
    }
}
