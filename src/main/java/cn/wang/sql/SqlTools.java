package cn.wang.sql;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlTools {
    static final String SEQ_ID = "SEQ_CH_ID.NEXTVAL";
    private static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

    private static String SPLIT_REGEX = "、|,|\\s+";

    /**
     * @Description 生成菜单表脚本
     * @param menuType
     * @param menuName
     * @param parentName
     * @param menuUrl
     * @param sort
     *            void
     */
    public static void generateMenu(int menuType, String menuName, String parentName, String menuUrl, int sort) {
        int parentMenuType = menuType - 1;
        menuUrl = menuUrl == null ? "#" : menuUrl;
        StringBuffer buf = new StringBuffer();
        buf.append(String.format("\n-- %s, 修改菜单表，增加 %s\n", f.format(new Date()), menuName));
        String result = String.format(
                "INSERT INTO CH_MENU( ID, MENU_NAME, MENU_PID, DESCRIPTION, MENU_URL, MENU_STATUS, MENU_TYPE, PRIVILEGESCODE, MENU_SORT ) \n"
                        + " VALUES( %s, '%s', \n" + " ( SELECT id FROM CH_MENU m WHERE m.MENU_TYPE = %d AND m.MENU_NAME = '%s' ), \n"
                        + " '%s', '%s', '0', '%d', 'm%s_%s', %d );",
                SEQ_ID, menuName, parentMenuType, parentName, menuName, menuUrl, menuType, menuType, menuName, sort);
        buf.append(result);
        System.out.println(buf.toString());
    }

    /**
     * @Description 生成字典表脚本
     * @param typeId
     * @param typeName
     * @param subNames
     *            void
     */
    public static void generateDictionary(int typeId, String typeName, String subNames) {
        String[] subNameArr = subNames.split(SPLIT_REGEX);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("-- 字典表增加" + typeName);
        for (String subName : subNameArr) {
            String sql = String.format("\nINSERT INTO CH_DICTIONARY\n" + "(ID, TYPE_ID, TYPE_NAME, SUB_CODE, SUB_NAME, DATA_STATE)\n"
                    + "VALUES(%s, %d, '%s', %d, '%s', 0);\n", SEQ_ID, typeId, typeName, count++, subName);
            sb.append(sql);
        }

        System.out.println(sb.toString());
    }

    static public void main(String[] args) {
        // generateMenu(4, "购买申请（保险）", "客户信息", null, 20);
        // generateMenu(3, "产品购买信息（保险）", "业务处理", "/subscribeInsurance/subscribeInsuranceList.do", 2);
        // generateMenu(4, "导出Excel", "产品购买信息（保险）", null, 0);
        // generateMenu(4, "查看详情", "产品购买信息（保险）", null, 0);
        // generateMenu(4, "查看页面", "产品购买信息（保险）", null, 0);
        //
        // generateMenu(4, "购买申请", "产品购买信息（保险）", null, 0);
        generateDictionary(3210, "称呼", "先生、女士");
        // generateDictionary(3021, "缴费期", "趸交保费、3年期、5年期、6年期、8年期、10年期、12年期、15年期、18年期、20年期、25年期");
        // 待部门经理审批 待业务推广部审批 待数据部审批 审批通过 审批不通过
        // generateDictionary(3023, "购买保险审批状态", "待部门经理审批 待业务推广部审批 待数据部审批 审批通过 审批不通过");

        // 产品周期类型(终身:1 自定义:2)

        // String s = "趸交保费,3年期,5年期、6年期、8年期、10年期、12年期、15年期、18年期、20年期、25年期";
        // String[] split = s.split("、|,|\\s+");
        //
        // for (String string : split) {
        // System.out.println(string);
        // }
    }

}
