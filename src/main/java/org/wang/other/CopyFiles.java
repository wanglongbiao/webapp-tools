package org.wang.other;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: wanglongbiao
 * @date: 2016年11月16日 上午10:35:20
 * @Description: 使用 Java 执行 Linux 命令，拷贝文件
 */
public class CopyFiles {

    private static String startDate, endDate, sourcePath, destPath, idsFilePath;

    static {
        try {
            InputStream openStream = CopyFiles.class.getResource("copy-files.properties").openStream();
            Properties p = new Properties();
            p.load(openStream);
            startDate = p.getProperty("startDate");
            endDate = p.getProperty("endDate");
            sourcePath = p.getProperty("sourcePath");
            destPath = p.getProperty("destPath");
            idsFilePath = p.getProperty("idsFilePath");
            openStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception {
        doCopyFiles();
    }

    public static void doCopyFiles() throws Exception {
        long startMillis = System.currentTimeMillis();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        Date start = f.parse(startDate);
        Date end = f.parse(endDate);


        Map<String, String> m = new HashMap<String, String>();
        m.put("0", "申请证明");
        m.put("1", "身份证明及婚姻证明");
        m.put("3", "工作证明及经营证明");
        m.put("4", "居住证明及资产证明");
        m.put("5", "银行流水");
        m.put("6", "征信报告");
        m.put("9", "共同借款人");
        m.put("7", "其他资料");
        m.put("8", "外访资料");
        // String source = "/14_data/chp_image/0/20150501/400000307226075/*";
        // String dest = "/tmp/400000307226075/";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(idsFilePath));
            String loanId = null;
            int i = 0;
            while ((loanId = reader.readLine()) != null) {
                i++;
                //
                for (String key : m.keySet()) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(start);

                    while (cal.getTime().compareTo(end) <= 0) {
                        String destFullPath = destPath + f.format(cal.getTime()) + "/" + loanId + "/" + m.get(key);
                        // System.out.println("===destPath===" + destFullPath);
                        File destDit = new File(destFullPath);

                        String dataStr = f.format(cal.getTime());
                        String source = sourcePath + key + "/" + dataStr + "/" + loanId;
                        File sourceFile = new File(source);
                        if (sourceFile.exists()) {
                            if (!destDit.exists()) {
                                destDit.mkdirs();
                            }
                            String cmd = "cp -r " + source + "/* " + destFullPath;
                            System.out.println(String.format("第\t%s条,cmd:%s", i, cmd));
                            exec(cmd);
                        }
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                    }
                }


            }

            System.out.println("总耗时（ms）：" + (System.currentTimeMillis() - startMillis));

            // String pwdString = exec("pwd").toString();
            // String pwdString2 = exec("cp " + source + " " + dest).toString();
            //
            // System.out.println(pwdString);
            // System.out.println(pwdString2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }



    public static Object exec(String cmd) {
        try {
            String[] cmdA = { "/bin/sh", "-c", cmd };
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
