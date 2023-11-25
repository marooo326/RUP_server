package com.rup.domain;

import com.rup.domain.enums.MemberStatus;
import com.rup.domain.enums.MemberType;
import com.rup.domain.enums.UserRole;
import com.rup.domain.mapping.PromiseMember;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    private Long point;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    @ColumnDefault("'USER'")
    private UserRole userRole;

    @Column(name = "oauth_id", unique = true, nullable = false)
    private String OAuthId;

    //    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToOne
    private Location location;

    @OneToMany(mappedBy = "member")
    private List<PromiseMember> promiseMembers;
}
