package pratice.roon.misedirt.common.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
public class Member {
    @Id
    private String username;

    @NotEmpty
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    MemberRole memberRole;

    public void setMemberRole(MemberRole memberRole){
        this.memberRole = memberRole;
    }
}