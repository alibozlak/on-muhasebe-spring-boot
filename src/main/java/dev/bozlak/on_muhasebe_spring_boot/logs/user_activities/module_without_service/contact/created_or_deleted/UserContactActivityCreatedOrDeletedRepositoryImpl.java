package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.contact.created_or_deleted;

import org.springframework.stereotype.Repository;

@Repository
@lombok.RequiredArgsConstructor
public class UserContactActivityCreatedOrDeletedRepositoryImpl implements UserContactActivityCreatedOrDeletedRepository
{
    private final JpaUserContactActivityCreatedOrDeletedRepository jpaUserContactActivityCreatedOrDeletedRepository;

    @Override
    public void add(Long contactId) {
        UserContactActivityCreatedOrDeleted userContactActivityCreatedOrDeleted
                = new UserContactActivityCreatedOrDeleted();
        userContactActivityCreatedOrDeleted.contactId = contactId;
        userContactActivityCreatedOrDeleted.isActivityCreate = true;
        userContactActivityCreatedOrDeleted.whichDay = java.time.LocalDate.now();

        this.jpaUserContactActivityCreatedOrDeletedRepository.save(userContactActivityCreatedOrDeleted);
    }
}
