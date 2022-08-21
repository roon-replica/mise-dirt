package pratice.roon.misedirt.common.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pratice.roon.misedirt.common.entity.MemberRole;

@AllArgsConstructor
@Builder
@Getter
public class MemberResponse {
    private String username;
    private String memberRole;
}
