package pratice.roon.misedirt.common.api.dto;

import lombok.Data;
import pratice.roon.misedirt.common.entity.MemberRole;

@Data
public class UpdateMemberRequest {
    private String username;
    private MemberRole memberRole;
}
