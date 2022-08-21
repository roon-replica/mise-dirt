package pratice.roon.misedirt.common.exception;

public class MemberNotFoundException extends RuntimeException {
    private String msg;

    public MemberNotFoundException(String msg) {
        this.msg = msg;
    }
}
