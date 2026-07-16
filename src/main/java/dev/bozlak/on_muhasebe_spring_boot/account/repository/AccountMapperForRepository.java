package dev.bozlak.on_muhasebe_spring_boot.account.repository;

import dev.bozlak.on_muhasebe_spring_boot.account.Account;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapperForRepository {

    Account toEntityFromItsCreateModel(CreateAccountRequestModel createAccountRequestModel);
}
