package org.demo.user.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.demo.user.application.interfaces.UserRelationRepository;
import org.demo.user.domain.User;
import org.demo.user.repository.entity.UserEntity;
import org.demo.user.repository.entity.UserRelationEntity;
import org.demo.user.repository.entity.UserRelationIdEntity;
import org.demo.user.repository.jpa.JpaUserRelationRepository;
import org.demo.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        // Relation 저장
        jpaUserRelationRepository.save(entity);
        // 팔로잉 수, 팔로워 수 저장
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

    @Override
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        // Relation 삭제
        jpaUserRelationRepository.deleteById(id);
        // 팔로잉 수, 팔로워 수 저장
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
