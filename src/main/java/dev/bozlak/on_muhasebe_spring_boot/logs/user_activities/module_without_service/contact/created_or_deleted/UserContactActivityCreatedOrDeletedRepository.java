package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.contact.created_or_deleted;

public interface UserContactActivityCreatedOrDeletedRepository {

    void add(Long contactId);
}
