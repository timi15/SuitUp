package hu.unideb.inf.suitup.service;

import hu.unideb.inf.suitup.entity.UserEntity;

public interface UserService {

    UserEntity register(UserEntity userEntity);

    Long getCurrentUserId();

}
