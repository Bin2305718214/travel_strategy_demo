package com.travel.utils;

import java.io.File;
import java.util.List;

/**
 * 文件工具类
 * @author Build_start
 */
public class FileUtils {

    /**
     * 删除单个文件
     * @param path 文件路径
     * @param fileName 文件名
     * @return 是否删除（true: 成功，false: 失败）
     */
    public static boolean delete(String path, String fileName) {
        File file = new File(path + fileName);

        boolean flag = file.delete();

        return flag;
    }

    /**
     * 批量删除文件
     * @param path 文件路径
     * @param postImgList 文件名列表
     * @return 是否全部删除成功（true: 成功，false: 失败）
     */
    public static boolean deleteAll(String path, List<String> postImgList) {
        File file = null;
        boolean flag = true;

        // 删除所有上传的图片
        for (String imgName : postImgList) {
            file = new File(path + imgName);
            boolean delete = file.delete();

            if (!delete) {
                flag = false;
                break;
            }
        }

        return flag;
    }

}
