package com.baidu.majia.utils;

import android.os.Environment;

import java.io.File;

public class FileManager {

    private static String homeDir;
    private static String logDir;
    private static String imgDownloadDir;

    /**
     * get app's home directory
     *
     * @return
     */
    public static String getHomeDir() {
        if (homeDir == null) {
            if (SysUtil.isSdExist()) {
                homeDir = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/MaJia";
            } else {
                homeDir = Environment.getRootDirectory()
                        .getAbsolutePath() + "/MaJia";
            }
        } else {
            File file = new File(homeDir);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return homeDir;
    }

    /**
     * get image cache directory
     *
     * @return
     */
    public static String getImgCacheDir() {
        return getHomeDir() + ImageUtil.TEMP_IMG_CACHE_FOLDER;
    }

    /**
     * get log directory
     *
     * @return
     */
    public static String getLogDir() {
        logDir = getHomeDir() + "/log";
        File file = new File(logDir);
        file.mkdirs();
        return logDir;
    }

    /**
     * get downloaded images' directory
     * @return
     */
    public static String getImgDownloadDir() {
        imgDownloadDir = getHomeDir() + "/download";
        File file = new File(imgDownloadDir);
        file.mkdirs();
        return imgDownloadDir;
    }

}
