package mazeneko.mazewands.example.exception;

import java.util.List;

import mazeneko.mazewands.example.member.domain.Member;
import mazeneko.mazewands.persistence.Identifier;

public final class MemberNotFoundException extends CustomException {
  private static final ErrorCode ERROR_CODE = ErrorCode.MEMBER_NOT_FOUND;
  private final List<Identifier<Member>> notFoundMemberIds;

  public MemberNotFoundException(Identifier<Member> notFoundMemberId) {
    super(ERROR_CODE, notFoundMemberId.toString());
    this.notFoundMemberIds = List.of(notFoundMemberId);
  }

  public MemberNotFoundException(List<Identifier<Member>> notFoundMemberIds) {
    super(ERROR_CODE, notFoundMemberIds.toString());
    this.notFoundMemberIds = notFoundMemberIds;
  }

  @Override
  public List<Identifier<Member>> getContents() {
    return notFoundMemberIds;
  }
}
