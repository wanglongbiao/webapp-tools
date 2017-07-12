package cn.wang.hibernate.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cn.wang.common.DatabaseUtil;
import cn.wang.common.NameTools;
import cn.wang.hibernate.HibernateTool;

public class HibernateToolImpl implements HibernateTool {

    private static String projectRootPath;

    private static String projectBasePackage;
    private String tableName = "t_role";
    private String entityClassName = "Role";// Role
    private String entityName = entityClassName.toLowerCase();// role

    static {
        init();
    }

    @Override
    public void generateEntity() {
        try {
            StringBuilder fileBuf = new StringBuilder();
            StringBuilder importBuf = new StringBuilder();
            StringBuilder classBuf = new StringBuilder();
            importBuf.append(String.format("package %s.%s.entity;", projectBasePackage, entityName));

            importBuf.append("import javax.persistence.Column;\n" + "import javax.persistence.Entity;\n" + "import javax.persistence.GeneratedValue;\n" + "import javax.persistence.GenerationType;\n"
                    + "import javax.persistence.Id;\n" + "import javax.persistence.Table;");

            classBuf.append("\n@Entity\n" + "@Table(name = \"t_role\")\n" + "public class Role {");

            String sql = String.format(
                    "select a.name 表名,b.name 字段名,c.name 字段类型,c.length 字段长度 from sysobjects a,syscolumns b,systypes c where a.id=b.id and a.name='%s' and a.xtype='U' and b.xtype=c.xtype", tableName);
            ResultSet resultSet = DatabaseUtil.executeQuery(sql);

            while (resultSet.next()) {
                String fieldName = resultSet.getString("字段名");
                String fieldType = resultSet.getString("字段类型");

                if (fieldName.toLowerCase().equals("id")) {
                    classBuf.append("    @Id\n" + "    @GeneratedValue(strategy = GenerationType.AUTO)\n" + "    private Long id;");
                } else {
                    String classFieldname = NameTools.underlineToCamel(fieldName);
                    classBuf.append(String.format("    @Column(name = \"%s\")\n" + "    private String %s;", fieldName, classFieldname));
                }
            }

            classBuf.append("\n}\n");
            fileBuf.append(importBuf);
            fileBuf.append(classBuf);

            System.out.println(fileBuf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
            projectRootPath = p.getProperty("rootPath");
            projectBasePackage = p.getProperty("basePackage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HibernateToolImpl hi = new HibernateToolImpl();
        hi.generateEntity();
    }

    @Override
    public void generateDao() {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateService() {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateController() {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateListJsp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateAddEditJsp() {
        // TODO Auto-generated method stub

    }

}
