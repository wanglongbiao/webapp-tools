package cn.wang.test;

import cn.wang.common.NameTools;

/**
 * @author: wanglongbiao
 * @date: 2016年6月17日 下午4:03:45
 * @Description:
 */
public class Test1 {


    public static void main(String[] args) throws Exception {
        String param = "userNameTest";
        String camelToUnderline = NameTools.camelToUnderline(param);
        System.out.println(camelToUnderline);

        System.out.println(NameTools.underlineToCamel("中文"));
    }

}
