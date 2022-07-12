package pratice.roon.misedirt.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pratice.roon.misedirt.common.api.dto.CreateMemberRequest;
import pratice.roon.misedirt.common.api.dto.CreateMemberResponse;
import pratice.roon.misedirt.common.api.dto.UpdateMemberRequest;
import pratice.roon.misedirt.common.api.dto.UpdateMemberResponse;
import pratice.roon.misedirt.common.entity.Member;
import pratice.roon.misedirt.common.service.MemberService;

import javax.validation.Valid;

@RestController
public class MemberApiController {
    @Qualifier("common")
    @Autowired
    private MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        String username = memberService.saveMember(member);
        return new CreateMemberResponse(username);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        String username = memberService.saveMember(Member.builder().username(request.getUsername()).build());
        return new CreateMemberResponse(username);
    }

    @PutMapping("/api/v2/members/{username}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("username") String username,
            @RequestBody UpdateMemberRequest request
    ) {
        memberService.update(username, request.getUsername());
        Member member = memberService.findByUsername(username);
        return new UpdateMemberResponse(member.getUsername());
    }
}
