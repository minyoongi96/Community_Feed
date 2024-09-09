package org.demo.user.repository.jpa;

import org.demo.user.repository.entity.UserRelationEntity;
import org.demo.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
