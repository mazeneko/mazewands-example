package mazeneko.mazewands.example.exception;

import lombok.Getter;

public sealed abstract class CustomException extends RuntimeException permits MemberNotFoundException {
  @Getter
  protected final ErrorCode errorCode;

  CustomException(ErrorCode errorCode) {
    super(createMessage(errorCode));
    this.errorCode = errorCode;
  }

  CustomException(ErrorCode errorCode, String detailMessage) {
    super(createMessage(errorCode, detailMessage));
    this.errorCode = errorCode;
  }

  CustomException(ErrorCode errorCode, String detailMessage, Throwable cause) {
    super(createMessage(errorCode, detailMessage), cause);
    this.errorCode = errorCode;
  }

  CustomException(ErrorCode errorCode, Throwable cause) {
    super(createMessage(errorCode), cause);
    this.errorCode = errorCode;
  }

  public abstract Object getContents();

  protected static String createMessage(ErrorCode errorCode) {
    return "[%s] %s".formatted(errorCode.value(), errorCode.description());
  }

  protected static String createMessage(ErrorCode errorCode, String detailMessage) {
    return "[%s] %s : %s".formatted(errorCode.value(), errorCode.description(), detailMessage);
  }
}
