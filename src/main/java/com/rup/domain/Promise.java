package com.rup.domain;

import com.rup.domain.enums.PromiseStatus;
import com.rup.domain.mapping.PromiseMember;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "promises")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Promise extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime promiseTime;

    @Column(nullable = false)
    private Long penalty;

    @Column(nullable = false)
    private String inviteCode;

    @Enumerated(EnumType.STRING)
    private PromiseStatus status;

    @OneToOne
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member author;

    @OneToMany(mappedBy = "promise")
    private List<PromiseMember> promiseMembers;
}
