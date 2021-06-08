package com.learn.utils;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName GetAcousticOfWords
 * @description
 * @date 2021/6/8 18:14
 * @since JDK 1.8
 */
public class GetNetResourceByUrl {
    public static File GetNetResourceByUrl(String path, Map<String, Object> params) throws MalformedURLException {
        StringBuffer sb = new StringBuffer();
        sb.append(path).append("?");
        for (Map.Entry<String, Object> stringStringEntry : params.entrySet()) {
            sb.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue());
            sb.append("&");
        }

        String urlPath = params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors
                        .collectingAndThen(Collectors
                                .joining("&"), paramsStr -> sb.append(paramsStr).toString()));

        URL url = new URL(urlPath);
        url.openStream()
//        FileUtils.copyURLToFile(,);
    }
}
