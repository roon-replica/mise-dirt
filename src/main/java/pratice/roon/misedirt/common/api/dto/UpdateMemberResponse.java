package pratice.roon.misedirt.common.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pratice.roon.misedirt.common.entity.MemberRole;

@AllArgsConstructor
@Data
public class UpdateMemberResponse {
    private String username;
    private MemberRole memberRole;
}
