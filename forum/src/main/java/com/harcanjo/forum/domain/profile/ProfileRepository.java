package com.harcanjo.forum.domain.profile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

	Page<Profile> findAllByActiveTrue(Pageable page);
}
