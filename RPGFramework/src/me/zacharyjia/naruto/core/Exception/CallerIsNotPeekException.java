package me.zacharyjia.naruto.core.Exception;

/**
 * 调用者不是栈顶场景异常
 * Created by jia19 on 2016/3/21.
 */
public class CallerIsNotPeekException extends RuntimeException {

    public CallerIsNotPeekException(String msg) {
        super(msg);
    }
}
