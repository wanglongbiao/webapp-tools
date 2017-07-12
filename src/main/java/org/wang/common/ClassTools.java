package org.wang.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author: wanglongbiao
 * @date: 2016年6月24日 下午3:01:47
 */
public class ClassTools {

    public static void main(String[] args) {
        String comments = "保险人,产品名称,保险单号,缴费期,缴费方式,产品周期,险种分类,审核状态";
        String[] split = comments.split(Constants.SPLIT_REGEX);
        for (String c : split) {
            // String nameByComment = getFieldNameByComment(c,
            // ChSubscribeInsurance.class);
            // System.out.println(nameByComment);
        }

    }

    private static final Logger log = Logger.getLogger(ClassTools.class);

    /**
     * @Description 根据注释返回一个字段
     * @param line
     * @param clazz
     * @return String
     */
    public static String getFieldNameByComment(String line, Class<?> clazz) {
        String inputValue = "";
        try {
            String label = StringUtils.remove(line.trim(), "：");

            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                Annotation[] annotations = field.getDeclaredAnnotations();
                if (annotations.length == 1) {
                    Annotation annotation = annotations[0];
                    String comment = (String) annotation.annotationType().getMethod("comment").invoke(annotation);
                    if (label.equals(comment)) {
                        inputValue = fieldName;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        inputValue = inputValue == "" ? line : inputValue;// 如果找不到则返回原值
        return inputValue;
    }

    /**
     * @Description 根据存在文件中的中文注释，返回一个字段列表
     * @param fileName
     * @param clazz
     * @return List<String>
     */
    public static List<String> getFildNameListByComment(File f, Class<?> clazz) {
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String comment = null;
            while ((comment = br.readLine()) != null) {
                String fieldName = getFieldNameByComment(comment, clazz);
                list.add(fieldName.length() > 0 ? fieldName : comment);
            }
            br.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * @Description 根据存在文件中的中文注释，返回一个字段列表
     * @param fileName
     * @param clazz
     * @return List<String>
     */
    public static List<String> getFildNameListByComment(String fileName, Class<?> clazz) {
        List<String> list = new ArrayList<String>();
        try {
            File f = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String comment = null;
            while ((comment = br.readLine()) != null) {
                String fieldName = getFieldNameByComment(comment, clazz);
                list.add(fieldName.length() > 0 ? fieldName : comment);
            }
            br.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
}
