package dev.bozlak.on_muhasebe_spring_boot.admin.service;

import dev.bozlak.on_muhasebe_spring_boot.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Short getAdminIdByUserId(Integer userId) {
        return this.adminRepository.getAdminIdByUserId(userId);
    }
}
