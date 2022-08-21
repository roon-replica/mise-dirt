package pratice.roon.misedirt.common.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pratice.roon.misedirt.common.api.dto.*;
import pratice.roon.misedirt.common.entity.Member;
import pratice.roon.misedirt.common.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberApiController {
    @Qualifier("common")
    @Autowired
    private MemberService memberService;

    @PostMapping("/api/v1/members")
    public Long saveMemberV1(@RequestBody @Valid Member member) {
        return memberService.createMember(member.getUsername(), member.getPassword(), member.getMemberRole());
    }

    @PostMapping("/api/v2/members")
    public Long saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        return memberService.createMember(request.getUsername(), request.getPassword(), request.getMemberRole());
    }

    @PutMapping("/api/v2/members/{username}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("username") String username,
            @RequestBody UpdateMemberRequest request) {

        memberService.update(username, request.getUsername());

        return new UpdateMemberResponse(request.getUsername(), request.getMemberRole());
    }

    // patch is not idempotent nor safe.
    @PatchMapping("/api/v2/members/{username}")
    public UpdateMemberResponse updateMemberNameOnly(@PathVariable("username") String username,
                                                     @RequestBody UpdateMemberNameOnly partialUpdate) {

        Member member = memberService.getUserByName(username);
        memberService.update(username, partialUpdate.getUsername());
        return new UpdateMemberResponse(member.getUsername(), member.getMemberRole());
    }

    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Response<List<MemberResponse>> membersV2() {
        List<MemberResponse> members = memberService.findMembers().stream()
                .map(member -> MemberResponse.builder()
                        .username(member.getUsername())
                        .memberRole(member.getMemberRole().name())
                        .build()
                )
                .collect(Collectors.toList());

//        return Response.builder()
//                .data(members)
//                .build();

        return new Response<>(members);
    }
}
