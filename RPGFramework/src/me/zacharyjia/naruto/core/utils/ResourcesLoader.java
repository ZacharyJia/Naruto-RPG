package me.zacharyjia.naruto.core.utils;

import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * 资源加载器
 * 用于通过从Jar包中获取指定文件的输入流
 *
 * Created by jia19 on 2016/3/21.
 */
public class ResourcesLoader {

    public static InputStream getInputStream(String file) throws ResourcesNotFoundException {
        InputStream is = ResourcesLoader.class.getResourceAsStream(file);
        if (is == null) {
            throw new ResourcesNotFoundException("Resources File" + file + " not found!");
        }
        return is;
    }
}
