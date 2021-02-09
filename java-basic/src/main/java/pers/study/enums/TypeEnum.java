package pers.study.enums;

import lombok.Getter;

/**
 * @Create: 2020-07-17 17:27
 * @description:
 * @author: candy
 **/
public enum  TypeEnum {
    FIREWALL("firewall"),
    SECRET("secretMac"),
    BALANCE("f5");

    @Getter
    private String typeName;

    TypeEnum(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 根据类型的名称，返回类型的枚举实例。
     *
     * @param typeName 类型名称
     */
    public static TypeEnum fromTypeName(String typeName) {
        for (TypeEnum type : TypeEnum.values()) {
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        return null;
    }
}
