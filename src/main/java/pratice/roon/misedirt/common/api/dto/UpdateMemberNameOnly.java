package pratice.roon.misedirt.common.api.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Data
public class UpdateMemberNameOnly {
    @NotNull
    private String username;
}
