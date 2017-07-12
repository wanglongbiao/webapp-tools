package com.wlb.sql;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VtsSqlTools {

    public static void main(String[] args) {

        Map<String, String> moduleMap = new LinkedHashMap<>();
        Map<String, String> operMap = new LinkedHashMap<>();

        // moduleMap.put("privilege", "权限管理");
        // moduleMap.put("org", "机构管理");
        // moduleMap.put("user", "用户管理");
        // moduleMap.put("role", "角色管理");

        // operMap.put("add", "新增");
        // operMap.put("edit", "修改");
        // operMap.put("view", "查看详情");
        // operMap.put("list", "查看列表");
        // operMap.put("delete", "删除");

        // moduleMap.put("mark", "标记权限管理");
        // moduleMap.put("goal", "目标管理");
        // moduleMap.put("radar", "雷达管理");
        // moduleMap.put("ais", "AIS基站管理");
        // moduleMap.put("video", "光电设备管理");
        // moduleMap.put("area", "区域管理");
        // moduleMap.put("replay", "回放管理");
        moduleMap.put("alert", "警报管理");
        moduleMap.put("weave", "测波管理");
        moduleMap.put("infomng", "信息管理器管理");

        operMap.put("view", "查看");
        // operMap.put("operate", "操作");

        generatePermission(moduleMap, operMap);
    }

    public static void generatePermission(Map<String, String> moduleMap, Map<String, String> operMap) {

        for (Entry<String, String> entry : moduleMap.entrySet()) {
            String moduleCode = entry.getKey();
            String moduleName = entry.getValue();

            System.out.println("-- " + moduleName + "=============");
            for (Entry<String, String> oper : operMap.entrySet()) {
                String operCode = oper.getKey();
                String operName = oper.getValue();

                String sql = String.format("INSERT INTO T_PRIVILEGE (PRIVILEGE_CODE,PRIVILEGE_NAME)VALUES('%s:%s', '%s-%s');", moduleCode, operCode, moduleName, operName);
                System.out.println(sql);
            }
            System.out.println();
        }


    }
}
