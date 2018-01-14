package com.client.repository;

import com.client.domain.ExtendedProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtendedProfileRepository extends JpaRepository<ExtendedProfile, Long> {
}
