package mazeneko.mazewands.example.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
  MEMBER_NOT_FOUND("メンバーが見つかりませんでした。"),
  ;

  private final String description;

  public String value() {
    return this.name();
  }

  public String description() {
    return this.description;
  }
}
