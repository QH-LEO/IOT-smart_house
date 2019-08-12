package org.AIvoice;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.*;

public class FileUtil {
    public FileUtil() {
    }

    public static byte[] read(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        byte[] data = inputStream2ByteArray(in);
        in.close();
        return data;
    }

    private static byte[] inputStream2ByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        boolean var3 = false;

        int n;
        while((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }

        return out.toByteArray();
    }

    public static void save(String filePath, String fileName, byte[] content) {
        try {
            File filedir = new File(filePath);
            if (!filedir.exists()) {
                filedir.mkdirs();
            }

            File file = new File(filedir, fileName);
            OutputStream os = new FileOutputStream(file);
            os.write(content, 0, content.length);
            os.flush();
            os.close();
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }
}
