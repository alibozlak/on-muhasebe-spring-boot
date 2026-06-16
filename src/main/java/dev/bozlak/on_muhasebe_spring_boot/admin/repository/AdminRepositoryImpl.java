package dev.bozlak.on_muhasebe_spring_boot.admin.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepository {

    private final JpaAdminRepository jpaAdminRepository;

    @Override
    public Short getAdminIdByUserId(Integer userId) {
        return this.jpaAdminRepository.getAdminIdByUserId(userId);
    }
}
