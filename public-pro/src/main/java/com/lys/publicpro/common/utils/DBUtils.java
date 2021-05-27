package com.lys.publicpro.common.utils;

import java.util.UUID;

public class DBUtils {
    public DBUtils() {};
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("[-]", "");
    }
}
