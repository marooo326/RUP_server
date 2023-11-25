package com.rup.domain;

import com.rup.domain.enums.MemberKeyword;
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

    @Enumerated(EnumType.STRING)
    private MemberKeyword keyword;

    //    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PromiseMember> promiseMembers;

    public void updateLocation(Location location) {
        this.location = location;
    }

    public void updatePoint(Long point) {
        this.point = point;
    }
}
