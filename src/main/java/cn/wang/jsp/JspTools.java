package cn.wang.jsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.wang.common.ClassTools;


/**
 * @author: wanglongbiao
 * @date: 2016年6月17日 上午9:45:32
 * @Description: JSP页面生成类
 */
public class JspTools {

    private static final Logger log = Logger.getLogger(JspTools.class);
    private static int TR = 4;
    private static final String PREFIX = "like_";
    private static final String SPLIT_REGEX = ",|\\s+|，|、";

    /**
     * @Description 根据中文字段，输出列表搜索项
     */
    public static void printListJspSearch(String comments, Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        try {
            String[] commentsArr = comments.split(SPLIT_REGEX);
            int count = 0;
            for (String comment : commentsArr) {

                if (count++ % TR == 0) {
                    sb.append(count == 0 ? "<tr>\n" : "\n</tr>\n<tr>\n");
                }

                String fieldName = ClassTools.getFieldNameByComment(comment, clazz);
                if (fieldName == null || fieldName.length() == 0) {
                    fieldName = comment;
                }

                boolean isDate = StringUtils.indexOfAny(comment, "日期", "时间") > -1;
                sb.append(
                        String.format("<td>\n<label>%s</label>\n<input type=\"text\" name=\"%s%s\" "
                                + (isDate ? "class=\"input-small Wdate width w100\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});\""
                                        : "")
                                + " value=\"" + (isDate ? "<fmt:formatDate value='${%s}' pattern='yyyy-MM-dd'/>" : "${%s%s}") + "\" disabled />\n"
                                + "</td>\n", comment, PREFIX, fieldName, PREFIX, fieldName));

            }

            sb.append("<td><input id=\"retrieve_btn\" class=\"form_now marginr10\" type=\"submit\" value=\"查询\"></td>");
            sb.append("\n</tr>\n");
            System.out.println(sb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @Description 输出列表页面查询条件代码
     * @param filePath
     * @param clazz
     */
    @Deprecated
    public static void printListJspSearch(File filePath, Class<?> clazz) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(filePath));
            String line = null;
            StringBuffer sb = new StringBuffer();
            int count = 0;
            while ((line = buf.readLine()) != null) {
                if (line.trim().length() == 0)
                    continue;

                if (count % TR == 0) {
                    sb.append(count == 0 ? "<tr>\n" : "\n</tr>\n<tr>\n");
                }

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
                boolean isDate = StringUtils.indexOfAny(line, "日期", "时间") > -1;

                inputValue = inputValue.length() == 0 ? label : inputValue;
                sb.append(
                        String.format("<td>\n<label>%s</label>\n<input type=\"text\" name=\"%s%s\" "
                                + (isDate ? "class=\"input-small Wdate width w100\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});\""
                                        : "")
                                + " value=\"" + (isDate ? "<fmt:formatDate value='${%s}' pattern='yyyy-MM-dd'/>" : "${%s%s}") + "\" disabled />\n"
                                + "</td>\n", line, PREFIX, inputValue, PREFIX, inputValue));
                count++;
            }

            sb.append("<td><input id=\"retrieve_btn\" class=\"form_now marginr10\" type=\"submit\" value=\"查询\"></td>");
            sb.append("\n</tr>\n");
            System.out.println(sb.toString());
            buf.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void printListTable(String comments, Class<?> clazz) {
        if (comments == null || comments.length() == 0) {
            return;
        }

        String[] commentsArr = comments.split(SPLIT_REGEX);
        if (commentsArr == null || commentsArr.length == 0) {
            return;
        }

        StringBuffer sb = new StringBuffer("<table id=\"myTable02\" class=\"fancyTable\">\n<thead>\n<tr>\n");
        // 加表头开始
        for (String comment : commentsArr) {
            comment = comment.trim();
            if (comment.length() == 0)
                continue;
            sb.append(String.format("<th>%s</th>\n", comment));
        }
        sb.append("</tr>\n</thead>\n");
        // 加表头结束

        // 加表体
        sb.append("<tbody>\n<c:forEach items=\"${pclist}\" var=\"item\" varStatus=\"status\">\n<tr>\n");

        for (String comment : commentsArr) {
            String fieldName = ClassTools.getFieldNameByComment(comment, clazz);
            if (fieldName == null || fieldName.length() == 0) {
                fieldName = comment;
            }

            boolean isDate = StringUtils.indexOfAny(comment, "日期", "时间") > -1;
            sb.append(String.format("<td>" + (isDate ? "<fmt:formatDate value=\"${item.%s}\" pattern=\"yyyy-MM-dd\" />" : "${item.%s }") + "</td>\n",
                    fieldName));

        }

        sb.append("</tr>\n</c:forEach>\n</tbody>\n</table>");
        System.out.println(sb.toString());

    }

    /**
     * @Description 输出列表页面的Table里的td代码
     * @param filePath
     * @param clazz
     *            void
     */
    @Deprecated
    public static void printListTable(File filePath, Class<?> clazz) {
    }

    /**
     * @Description 输出新增、修改、查看页面的td标签
     * @param filePath
     */
    public static void printOperateJspTds(String filePath, Class<?> clazz, String poName) {
        if (poName == null) {
            poName = "xxxx";
        }
        StringBuilder sb = new StringBuilder();
        try {
            File f = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String readLine = null;
            int count = 0;
            while ((readLine = br.readLine()) != null) {
                if (count++ % 3 == 0) {
                    sb.append("</tr>\n\n<tr>\n");
                }
                Set<String> set = new HashSet<String>();
                set.add(readLine);
                String fieldName = StringUtils.remove(readLine, "：");
                for (Field field : clazz.getDeclaredFields()) {
                    String name = field.getName();
                    Annotation[] annotations = field.getDeclaredAnnotations();
                    for (Annotation annotation : annotations) {
                        String comment = (String) annotation.annotationType().getMethod("comment").invoke(annotation);
                        if (fieldName.equals(comment)) {
                            sb.append(String.format(
                                    "<td><label>%s：</label><input id='%s' name='%s' class='bggry'  type='text' value='${%s.%s}' ></td>\n", readLine,
                                    name, name, poName, name));
                            set.remove(readLine);
                        }
                    }
                }
                if (!set.isEmpty()) {
                    sb.append(count + "," + set);
                }
            }
            br.close();
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 生成消息提醒列表
     */
    public static void generateMessageList(String fieldsStr, String seporator, Class<?> clazz) {

        StringBuffer th = new StringBuffer();
        StringBuffer trHead = new StringBuffer();
        StringBuffer trBody = new StringBuffer();
        StringBuffer td = new StringBuffer();
        StringBuffer tbody = new StringBuffer();
        StringBuffer table = new StringBuffer();
        StringBuffer ifTag = new StringBuffer();
        StringBuffer foreachTag = new StringBuffer();
        try {
            String[] fields = StringUtils.split(fieldsStr, seporator);
            for (String f : fields) {
                th.append(String.format("<th>%s</th>", f));
                if (f.equals("序号")) {
                    td.append("<td align=\"center\">${status.index+1}</td>");
                    continue;
                }
                String fieldName = ClassTools.getFieldNameByComment(f, clazz);
                td.append(String.format("<td align=\"center\">${item.%s}</td>", fieldName != "" ? fieldName : f));
            }
            th.append(String.format("<th>%s</th>", "操作"));
            td.append("<td align=\"center\"><span onclick=\"linkDetails('${item.id}')\">查看</span></td>");

            trHead.append(String.format("<thead><tr>%s</tr></thead>", th.toString()));

            trBody.append(String.format(
                    "<tr class=\"data\" onclick=\"readedMessage('${item.messageId}')\" <c:if test=\"${item.messageStatus==0}\">style=\"font-weight: bold;\"</c:if>>%s</tr>",
                    td));
            foreachTag.append(String.format("<c:forEach items=\"${list}\" var=\"item\" varStatus=\"status\">%s</c:forEach>", trBody));
            tbody.append(String.format("<tbody>%s</tbody>", foreachTag));
            table.append(String.format(
                    "<table class=\"X_Tab X_tablefom\" width=\"100%%\"  >%s</table><div class=\"t_content\" style=\"height: 385px;overflow:auto;\" /><table>%s</table>",
                    trHead, tbody));
            ifTag.append(String.format("<c:if test=\"${messageType==}\">%s</c:if>", table));
            System.out.println(ifTag);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void generateJqueryValidateCode(String fileName, Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        StringBuilder fieldsStr = new StringBuilder();

        List<String> fields = ClassTools.getFildNameListByComment(fileName, clazz);
        for (String field : fields) {
            fieldsStr.append(String.format("'%s' : {required : true},", field));
        }
        sb.append(String.format("$(\"#\").validate({rules : {%s},});", fieldsStr));

        System.out.println(sb);
    }

    public static void main(String[] args) {
        String fieldsStr = null;// "序号、客户编号、客户姓名/机构名称、私人财富顾问、身份证号";
        fieldsStr = "序号、客户编号、客户姓名/机构名称、私人财富顾问、到期日期、距到期天数";
        fieldsStr = "序号、客户类型、客户编号、客户姓名/机构名称、私人财富顾问";
        fieldsStr = "序号、客户类型、客户编号、客户姓名/机构名称、私人财富顾问、顾问工号、私人财富顾问(新)、顾问工号(新)、转增日期";
        fieldsStr = "序号、客户类型、客户编号、客户姓名/机构名称、私人财富顾问";
        fieldsStr = "投保人,产品名称,保险单号,产品周期,险种分类,审核状态,缴费期,缴纳方式";
        String f = "保险人,产品名称,保险单号,缴费期,缴费方式,产品周期,险种分类,审核状态,操作";

        // printListJspSearch(fieldsStr, ChSubscribeInsurance.class);

        // printListTable(f, ChSubscribeInsurance.class);

        // String seporator = "、";
        // Class<?> clazz = ChSubscribeProduct.class;
        // clazz = ChCustomer.class;
        // // h5ToValidate();
        // generateMessageList(fieldsStr, seporator, clazz);

        // generateJqueryValidateCode("d:/f.txt", ChSubscribeInsurance.class);
    }
}
