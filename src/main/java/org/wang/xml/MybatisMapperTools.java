package org.wang.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.wang.common.ClassTools;
import org.wang.common.Constants;
import org.wang.common.NameTools;
import org.xml.sax.InputSource;

/**
 * @author: wanglongbiao
 * @date: 2016年6月17日 下午4:15:58
 */
public class MybatisMapperTools {
    public static void main(String[] args) {
        String comments = "投保人,产品名称,保险单号,产品周期,险种分类,审核状态,缴费期,缴纳方式,";
        // printWhereClause(comments, ChSubscribeInsurance.class, true, "si");
        // modify();
    }

    private static final Logger log = Logger.getLogger(MybatisMapperTools.class);
    private static final String LIKE_PREFIX = "like_";

    /**
     * @Description 根据 中文名称生成 where 子句
     */
    public static void printWhereClause(String comments, Class<?> clazz, boolean isLike, String tableAlias) {
        tableAlias = tableAlias == null ? "xx" : tableAlias;
        try {
            String[] commentsArr = comments.split(Constants.SPLIT_REGEX);
            if (commentsArr == null || commentsArr.length == 0) {
                return;
            }


            StringBuilder sb = new StringBuilder();
            for (String comment : commentsArr) {
                String fieldName = ClassTools.getFieldNameByComment(comment, clazz);
                if (isLike) {
                    String likeFieldName = LIKE_PREFIX + fieldName;
                    sb.append(String.format("<if test=\"%s != null and %s != ''\">\n" + "           and %s.%s like '%%#{%s}%%'\n" + "       </if>",
                            likeFieldName, likeFieldName, tableAlias, NameTools.camelToUnderline(fieldName).toUpperCase(), likeFieldName));
                } else {
                    sb.append(String.format("<if test=\"%s != null and %s != ''\">\n" + "           and %s.%s=${%s}\n" + "       </if>", fieldName,
                            fieldName, tableAlias, NameTools.camelToUnderline(fieldName).toUpperCase(), fieldName));

                }
            }

            System.out.println(sb.toString());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    public static void printSelect(String filePath, Class<?> clazz) {
        try {
            String line = null;
            BufferedReader r = new BufferedReader(new FileReader(filePath));
            StringBuffer sb = new StringBuffer("select \n");
            while ((line = r.readLine()) != null) {
                if (line.length() == 0)
                    continue;

                String label = StringUtils.remove(line.trim(), "：");
                String inputValue = "";

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

                sb.append(String.format("%s,--%s\n", inputValue, line));
            }
            r.close();
            System.out.println(sb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void modify() {
        String fileName = "D:\\eclipse_workspace\\XH_INNO\\src\\cn\\xhcf\\customer\\dao\\InnBankTransferMapper.xml";
        try {
            Document doc = (Document) new SAXBuilder().build(new InputSource(new FileReader(new File(fileName))));
            Element root = doc.getRootElement();
            System.out.println(root.getName());
            Filter<Element> filter = new ElementFilter("sql");
            Iterator<Element> iterator = root.getDescendants(filter);
            if (iterator.hasNext()) {
                Element element = iterator.next();
                System.out.println(element.getText());
                // return element.getTextNormalize().trim();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

}
