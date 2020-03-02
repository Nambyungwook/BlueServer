package com.nbw.blue.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public abstract class UidGenerator {

    public static String make() {
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hhmmss = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSS"));
        String lpadId = StringUtils.leftPad(hhmmss, 8, "9");

        String uid = Base62.fromBase10(NumberUtils.toInt(lpadId)) + Base62.fromBase10(NumberUtils.toInt(today));

        log.info("generate uid: {} -> {}", today+","+hhmmss, uid);
        return uid;

    }
}
