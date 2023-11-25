package com.rup.domain;

import com.rup.domain.enums.MemberStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long point;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToOne(mappedBy = "member")
    private Location location;

    @OneToMany(mappedBy = "member")
    private List<PromiseMember> promiseMembers;
}
