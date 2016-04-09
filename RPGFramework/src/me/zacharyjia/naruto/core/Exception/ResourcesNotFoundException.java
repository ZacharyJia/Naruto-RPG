package me.zacharyjia.naruto.core.Exception;

import java.io.IOException;

/**
 * 资源不存在异常
 * Created by jia19 on 2016/3/21.
 */
public class ResourcesNotFoundException extends IOException {

    public ResourcesNotFoundException(String msg) {
        super(msg);
    }
}
