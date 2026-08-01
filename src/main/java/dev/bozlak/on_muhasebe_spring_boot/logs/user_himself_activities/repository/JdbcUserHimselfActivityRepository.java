package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.repository;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.UserHimselfActivity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcUserHimselfActivityRepository extends ListCrudRepository<UserHimselfActivity, Long> {
}
