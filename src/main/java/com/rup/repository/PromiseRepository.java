package com.rup.repository;

import com.rup.domain.Member;
import com.rup.domain.Promise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromiseRepository extends JpaRepository<Promise, Long> {
    Promise findByInviteCode(String inviteCode);

    List<Promise> findAllByAuthor(Member member);
}
