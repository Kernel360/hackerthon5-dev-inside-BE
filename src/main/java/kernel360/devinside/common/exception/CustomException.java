package kernel360.devinside.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ErrorCode errorCode;
    public CustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
