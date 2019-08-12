package org.AIvoice;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpUtil {
    public HttpUtil() {
    }

    public static Map<String, Object> doPost2(String url, Map<String, String> header, String body) {
        Map<String, Object> resultMap = new HashMap();
        PrintWriter out = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
            Iterator var9 = header.keySet().iterator();

            String responseContentType;
            while(var9.hasNext()) {
                responseContentType = (String)var9.next();
                httpURLConnection.setRequestProperty(responseContentType, (String)header.get(responseContentType));
            }

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            out.print(body);
            out.flush();
            if (200 != httpURLConnection.getResponseCode()) {
                System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
                return null;
            } else {
                responseContentType = httpURLConnection.getHeaderField("Content-Type");
                if ("audio/mpeg".equals(responseContentType)) {
                    byte[] bytes = toByteArray(httpURLConnection.getInputStream());
                    resultMap.put("Content-Type", "audio/mpeg");
                    resultMap.put("sid", httpURLConnection.getHeaderField("sid"));
                    resultMap.put("body", bytes);
                    return resultMap;
                } else {
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    String line;
                    String result;
                    for(result = ""; (line = in.readLine()) != null; result = result + line) {
                    }

                    resultMap.put("Content-Type", "text/plain");
                    resultMap.put("body", result);
                    return resultMap;
                }
            }
        } catch (Exception var12) {
            return null;
        }
    }

    public static String doPost1(String url, Map<String, String> header, String body) {
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
            Iterator var10 = header.keySet().iterator();

            String line;
            while(var10.hasNext()) {
                line = (String)var10.next();
                httpURLConnection.setRequestProperty(line, (String)header.get(line));
            }

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            out.print(body);
            out.flush();
            if (200 != httpURLConnection.getResponseCode()) {
                System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
                return null;
            } else {
                for(in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())); (line = in.readLine()) != null; result = result + line) {
                }

                return result;
            }
        } catch (Exception var11) {
            return null;
        }
    }

    private static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        boolean var3 = false;

        int n;
        while((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }

        return out.toByteArray();
    }
}
