package com.hw.xyls.tool;

import com.hw.xyls.web.Url;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by gaowenfeng on 2017/6/25.
 */
public class ZipUtil {

    public static List<String> decompressing(HttpServletRequest request, String uploadPath, MultipartFile multipartFile) {
//        long startTime = System.currentTimeMillis();
        List<String> list = new ArrayList<>();
        try {
            Charset gbk = Charset.forName("gbk");
            ZipInputStream Zin = new ZipInputStream(multipartFile.getInputStream(),gbk);//输入源zip路径
            BufferedInputStream Bin = new BufferedInputStream(Zin);

            String path = request.getServletContext().getRealPath(
                    "/");

            String userPath = "/" + uploadPath + "/";
            String realPath = path + userPath;                    //输出路径（文件夹目录）
            File fout = null;
            ZipEntry entry = null;
            do {
                try {
                    entry = Zin.getNextEntry();
                    UUID uuid = UUID.randomUUID();
                    String f;
                    String suffix = "";
                    suffix = (f = entry.getName()).substring(f.lastIndexOf("."));
                    if (suffix.equals(".png") || suffix.equals(".jpg")) {
                        String filename = uuid.toString() + suffix;
                        fout = new File(realPath, filename);
                        if (!fout.isDirectory() && !entry.getName().startsWith("__MACOSX") && !entry.getName().endsWith(".DS_Store")) {
                            if (!fout.exists()) {
                                (new File(fout.getParent())).mkdirs();
                            }
                            FileOutputStream out = new FileOutputStream(fout);
                            BufferedOutputStream bout = new BufferedOutputStream(out);
                            int b;
                            while ((b = Bin.read()) != -1) {
                                bout.write(b);
                            }
                            list.add(userPath.substring(1) + filename);
                            bout.close();
                            out.close();
                        }
                    }
                } catch (Exception e) {
                }finally {
                }
            } while (entry != null);
            Bin.close();
            Zin.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            list.clear();
        } catch (IOException e) {
            e.printStackTrace();
            list.clear();
        } finally {

//            long endTime = System.currentTimeMillis();
//            System.out.println("耗费时间： " + (endTime - startTime) + " ms");
            return list;
        }

    }
}
