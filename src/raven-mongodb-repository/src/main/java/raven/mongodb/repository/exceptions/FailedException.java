package raven.mongodb.repository.exceptions;

/**
 * @author yi.liang
 * @since JDK1.8
 */
public class FailedException extends Exception {
    public FailedException() {
        super("失败异常");
    }

    public FailedException(String message) {
        super(message);
    }
}
