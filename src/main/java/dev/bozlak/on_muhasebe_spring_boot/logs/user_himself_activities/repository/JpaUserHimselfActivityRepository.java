package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.repository;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.UserHimselfActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserHimselfActivityRepository extends JpaRepository<UserHimselfActivity, Long> {
}
