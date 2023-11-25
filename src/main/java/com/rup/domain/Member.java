package com.rup.domain;

import com.rup.domain.enums.MemberStatus;
import com.rup.domain.enums.MemberType;
import com.rup.domain.mapping.PromiseMember;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToOne
    private Location location;

    @OneToMany(mappedBy = "member")
    private List<PromiseMember> promiseMembers;
}
