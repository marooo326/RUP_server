package com.rup.repository;

import com.rup.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByOAuthId(String oAuthId);

    Member findByOAuthId(String oAuthId);
}
