package com.rup.domain.mapping;

import com.rup.domain.BaseEntity;
import com.rup.domain.Member;
import com.rup.domain.Promise;
import com.rup.domain.enums.PromiseMemberStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "promises_members")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class PromiseMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "promise_id")
    private Promise promise;

    @Enumerated(EnumType.STRING)
    private PromiseMemberStatus status;

    public void updateStatus(PromiseMemberStatus status) {
        this.status = status;
    }
}
