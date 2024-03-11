package mazeneko.mazewands.example.member.usecase;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mazeneko.mazewands.example.member.domain.Member;
import mazeneko.mazewands.example.member.domain.MemberCommand;
import mazeneko.mazewands.persistence.Identifier;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
  private final MemberCommand memberCommand;

  public Member createDummyMember() {
    final var member = memberCommand.save(
        new Member(
            Identifier.randomUUID(),
            "m-" + LocalDateTime.now(),
            List.of("hoge", "fuga")));
    log.info("会員ID[{}]を作成しました。", member.id());
    return member;
  }
}
