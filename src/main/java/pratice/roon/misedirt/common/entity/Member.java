package pratice.roon.misedirt.common.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

//    @OneToMany(mappedBy = "member")
//    List<Order> orders;

    public void setMemberRole(MemberRole memberRole){
        this.memberRole = memberRole;
    }
}