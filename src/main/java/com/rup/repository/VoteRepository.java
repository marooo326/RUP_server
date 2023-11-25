package com.rup.repository;

import com.rup.domain.Member;
import com.rup.domain.Promise;
import com.rup.domain.mapping.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByPromiseAndTarget(Promise promise, Member target);

}
