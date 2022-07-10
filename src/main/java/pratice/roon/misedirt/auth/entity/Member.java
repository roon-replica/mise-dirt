package pratice.roon.misedirt.auth.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Entity
public class Member {
    @Id
    private String username;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    MemberRole memberRole;

    public void setMemberRole(MemberRole memberRole){
        this.memberRole = memberRole;
    }
}