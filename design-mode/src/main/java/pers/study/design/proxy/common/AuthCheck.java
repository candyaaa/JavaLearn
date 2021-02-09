package pers.study.design.proxy.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiKui
 * @descrption: 权限校验
 */
@Slf4j
public class AuthCheck {
    public static boolean authCheck() {
        log.info("执行前验证用户权限...");
        return true;
    }
}
