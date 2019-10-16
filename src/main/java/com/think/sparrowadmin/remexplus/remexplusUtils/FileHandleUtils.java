/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.remexplusUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author map6
 */
public class FileHandleUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileHandleUtils.class);

    public static String readFileFromClassPath(String filePath) {
        //ResourceUtils.getFile("classpath:template");
        Resource resource = new ClassPathResource(filePath);
        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        LOG.debug("read file from filePath" + file.getAbsolutePath());
        String fileContent = readFileByChars(file);
        return fileContent;
    }

    public static String readFileByChars(File file) {
        Reader reader = null;
        StringBuffer fileContent = new StringBuffer();
        try {
            //以字符为单位读取文件内容，一次读一个字节
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    fileContent.append((char) tempchar);
                    //System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileContent.toString();
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符等
     *
     * @param str
     * @return
     */
    public static String replaceSpecialStr(String str) {
        String repl = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            repl = m.replaceAll("");
        }
        return repl;
    }


    /**
     * @args command line arguments
     */
    public static void main(String[] args) {
        String filePath = "C:\\sparrowUpload\\2019-10-11\\pom-submit-cache-751.xml";
        String fileCotent = readFileByChars(new File(filePath));

        int begin = fileCotent.indexOf("<dependencies>");
        int end = fileCotent.indexOf("</dependencies>");

        String subString = fileCotent.substring(begin + 14, end);
        System.out.println(replaceSpecialStr(subString));

    }

}
