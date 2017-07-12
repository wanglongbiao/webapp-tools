package cn.wang.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.wang.common.DatabaseUtil;
import cn.wang.map.utils.CRSConvertor;

public class Client {

    public static void main(String[] args) {

        // double x = 107.33280960718788;
        // double y = 19.956161191908173;
        //
        // y = 18.04;
        // double[] target = CRSConvertor.transform(x, y);
        // System.out.println("$$$$" + target[0] + "," + target[1]);

        excecute();
    }

    public static void excecute() {
        String sql = "SELECT id,LEFT_TOP_X,LEFT_TOP_Y,RIGHT_BOTTOM_X,RIGHT_BOTTOM_Y FROM T_TILE_POSITION_bak";
        ResultSet rs = DatabaseUtil.executeQuery(sql);

        StringBuilder all = new StringBuilder("update T_TILE_POSITION_bak set left_top_y=case id");
        StringBuilder idStr = new StringBuilder();
        StringBuilder lyStr = new StringBuilder();
        StringBuilder ryStr = new StringBuilder();

        long start = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long left_top_x = rs.getLong("LEFT_TOP_X");
                long left_top_y = rs.getLong("LEFT_TOP_Y");
                long right_bottom_x = rs.getLong("RIGHT_BOTTOM_X");
                long right_bottom_y = rs.getLong("RIGHT_BOTTOM_Y");
                
                double index = 1e6;
                double lx = left_top_x / index;
                double ly = left_top_y / index;
                double rx = right_bottom_x / index;
                double ry = right_bottom_y / index;


                double[] leftTarget = CRSConvertor.transform(lx, ly);
                double[] rightTarget = CRSConvertor.transform(rx, ry);

//                System.out.println(String.format("id:%s,x:%s,y:%s,rx:%s,ry:%s\tlx:%s\tly:%s\t\t\t\t\t\t\t\t\t", id, lx, ly, rx, ry, leftTarget[0], leftTarget[1]));

                int targetLy = (int) (leftTarget[1] * index);
                int targetRy = (int) (rightTarget[1] * index);
                // String updateSql = String.format("update T_TILE_POSITION_bak
                // set left_top_y=%d, right_bottom_y=%d where id=%s;", targetLy,
                // targetRy, id);

                idStr.append("," + id);

                lyStr.append(String.format("\nwhen %s then %s", id, targetLy));
                ryStr.append(String.format("\nwhen %s then %s", id, targetRy));
                // sb.append(String.format("update T_TILE_POSITION_bak set
                // left_top_y=%d, right_bottom_y=%d where id=%s;\n", targetLy,
                // targetRy, id));
                // System.out.println(updateSql);
            }


            all.append(lyStr).append(" end,right_bottom_y=case id ").append("").append(" \nend where id in (").append(idStr).append(")");
            System.out.println(all.toString());
            // System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
