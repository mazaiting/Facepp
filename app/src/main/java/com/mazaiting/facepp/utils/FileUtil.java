package com.mazaiting.facepp.utils;

import android.content.Context;

import com.mazaiting.facepp.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author mazaiting
 * @date 2018/1/30
 */

public class FileUtil {
    /**
     * 拷贝文件
     * @param context 上下文
     * @param sourceFile 源文件
     * @param destFile 目标文件
     */
    public static void copyFile(Context context, String sourceFile, String destFile) {
        File file = new File(destFile.substring(0, destFile.lastIndexOf("/")));
        if (!file.exists()) {
            file.mkdirs();
        }
        try{
            InputStream is = context.getResources().getAssets().open(sourceFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
            fos.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
