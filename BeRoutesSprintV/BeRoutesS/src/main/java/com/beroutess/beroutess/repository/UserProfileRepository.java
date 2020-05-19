package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import com.beroutess.beroutess.domain.UserProfile;

@Service
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
