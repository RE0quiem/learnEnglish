package com.learn.utils;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public static byte[] GetNetResourceByUrl(String path, Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        sb.append(path).append("?");
        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            sb.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue());
            sb.append("&");
        }

        String urlPath = params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors
                        .collectingAndThen(Collectors
                                .joining("&"), paramsStr -> sb.append(paramsStr).toString()));


        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] resultBytes = null;
        URL url;
        try {
            url = new URL(urlPath);
            inputStream = url.openStream();
            outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int rc = 0;
            while ((rc = inputStream.read(bytes, 0, 1024)) > 0) {
                outputStream.write(bytes, 0, rc);
            }
            resultBytes = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultBytes;
    }
}
