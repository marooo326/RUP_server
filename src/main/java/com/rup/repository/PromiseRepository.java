package com.rup.repository;

import com.rup.domain.Member;
import com.rup.domain.Promise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PromiseRepository extends JpaRepository<Promise, Long> {
    Optional<Promise> findByInviteCode(String inviteCode);

    List<Promise> findAllByAuthor(Member member);
}
