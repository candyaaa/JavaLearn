package github.candy.java.learn.gof.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiKui
 * @descrption 日志记录
 */
@Slf4j
public class Report {
    public static void recordLog() {
      log.info("记录日志并上报日志系统...");
    }
}
