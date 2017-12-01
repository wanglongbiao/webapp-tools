INSERT INTO T_STATISTICS (UNIT_ID, STATISTICS_NAME, STATISTICS_TYPE, [COUNT])

  SELECT
    mu.id,
    mu.MANAGEMENT_NAME,
    2,
    count(rd.id) ct
  FROM T_MANAGEMENT_UNIT mu
    LEFT JOIN T_ADDR ad ON ad.MANAGEMENT_ID = mu.id
    LEFT JOIN shipinfo si ON si.village = ad.addr_name
    LEFT JOIN t_realdata rd ON rd.devid = si.devid
  GROUP BY mu.management_name, mu.ID
  ORDER BY mu.id;

