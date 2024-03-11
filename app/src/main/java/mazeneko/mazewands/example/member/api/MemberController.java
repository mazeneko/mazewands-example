package mazeneko.mazewands.example.member.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mazeneko.mazewands.example.exception.MemberNotFoundException;
import mazeneko.mazewands.example.member.domain.Member;
import mazeneko.mazewands.example.member.domain.MemberQuery;
import mazeneko.mazewands.example.member.usecase.MemberService;
import mazeneko.mazewands.persistence.Identifier;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final MemberQuery memberQuery;

  @GetMapping
  public List<Member> getAll() {
    return this.memberQuery.getAll();
  }

  @GetMapping("{memberId}")
  public Member getById(@PathVariable("memberId") Identifier<Member> memberId) {
    return this.memberQuery
        .getById(memberId)
        .ensureOrThrow(MemberNotFoundException::new);
  }

  @PostMapping
  public Member createDummyMember() {
    return this.memberService.createDummyMember();
  }
}
