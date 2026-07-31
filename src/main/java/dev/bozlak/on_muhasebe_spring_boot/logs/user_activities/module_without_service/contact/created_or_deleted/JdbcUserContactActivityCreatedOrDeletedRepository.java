package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.contact.created_or_deleted;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcUserContactActivityCreatedOrDeletedRepository
        extends ListCrudRepository<UserContactActivityCreatedOrDeleted, Long>
{
}
