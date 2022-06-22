package com.augenstern.service.Util;

import java.util.Arrays;
import java.util.List;

/**
 * 标签工具类
 */
public class TagUtil {

    static final List<String> Tags;

    static {
        Tags = Arrays.asList(
                "笔记", "日常", "技术", "后端", "前端",
                "c与c++", "java", "python", "数据库",
                "Go"
        );
    }

    /**
     * 检查名称合法性
     *
     * @return true为合格  false为非法
     */
    public static boolean CheckTagsName(String tag) {
        for (String t : Tags) {
            if (t.equals(tag)) return true;
        }
        return false;
    }

    /**
     * Tag类型转换数据库id
     */
    public static boolean ChangeTagsId(List<String> tags) {
        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            if (CheckTagsName(tag)) {
                tags.set(i, String.valueOf(Tags.indexOf(tag) + 1));
            } else {
                return false;
            }
        }
        return true;
    }
}

