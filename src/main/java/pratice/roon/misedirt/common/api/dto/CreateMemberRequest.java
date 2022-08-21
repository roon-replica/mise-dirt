package pratice.roon.misedirt.common.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pratice.roon.misedirt.common.entity.MemberRole;

@AllArgsConstructor
@Data
public class CreateMemberRequest{   //todo : id 넣어야하는데 귀찮으니 일단 보류
    private String username;
    private MemberRole memberRole;
    private String password; // todo: 비밀번호... 클라이언트에서 암호화해서 보내야 하나?
}
