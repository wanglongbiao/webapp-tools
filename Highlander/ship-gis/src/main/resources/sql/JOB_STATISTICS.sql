-- 每次更新前先删除就数据
DELETE FROM T_STATISTICS;

-- 插入行政区域的统计信息
INSERT INTO T_STATISTICS (UNIT_ID, STATISTICS_NAME, STATISTICS_TYPE, SHIP_COUNT, REAL_COUNT, LOWBAT_COUNT)

  SELECT
    ad.ID,
    ad.ADDR_NAME,
    1                      type,
    COUNT(DISTINCT si.id)  ship_count,
    COUNT(DISTINCT rd.id)  real_count,
    COUNT(DISTINCT rd2.id) bat_count
  FROM T_ADDR ad
    LEFT JOIN shipinfo si ON si.city = ad.ADDR_NAME
    LEFT JOIN T_REALDATA rd ON rd.DEVID = si.devid AND DATEDIFF(MINUTE, rd.POSTIME, GETDATE()) <= 10
    LEFT JOIN T_REALDATA rd2 ON rd2.DEVID = si.devid AND DATEDIFF(MINUTE, rd.POSTIME, GETDATE()) <= 10 AND rd2.BAT <= 10
  GROUP BY ad.ID, ad.ADDR_NAME, ad.ADDR_LEVEL
  ORDER BY ad.ADDR_LEVEL, ad.ID;

-- 插入统计管理单位的统计信息
INSERT INTO T_STATISTICS (UNIT_ID, STATISTICS_NAME, STATISTICS_TYPE, SHIP_COUNT, REAL_COUNT, LOWBAT_COUNT)

  SELECT
    mu.id,
    mu.MANAGEMENT_NAME,
    2,
    COUNT(DISTINCT si.id),
    COUNT(DISTINCT rd.id)  real_count,
    COUNT(DISTINCT rd2.id) bat_count
  FROM T_MANAGEMENT_UNIT mu
    LEFT JOIN T_ADDR ad ON ad.MANAGEMENT_ID = mu.id
    LEFT JOIN shipinfo si ON si.village = ad.addr_name
    LEFT JOIN t_realdata rd ON rd.DEVID = si.devid AND DATEDIFF(MINUTE, rd.POSTIME, GETDATE()) <= 10
    LEFT JOIN T_REALDATA rd2 ON rd2.DEVID = si.devid AND DATEDIFF(MINUTE, rd.POSTIME, GETDATE()) <= 10 AND rd2.BAT <= 10
  GROUP BY mu.management_name, mu.ID
  ORDER BY mu.id;