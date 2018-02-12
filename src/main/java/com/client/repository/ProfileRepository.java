package com.client.repository;

import com.client.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile getProfileByUsername(String username);

}
