package pers.study.enums;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Create: 2020-07-17 17:15
 * @description:
 * @author: candy
 **/
@Slf4j
public class EnumTest {
    @Test
    public void colorEnumTest(){
        for (ColorEnum colorEnum : ColorEnum.values()){
            log.info("序列表:{}===枚举名:{}",colorEnum.ordinal(),colorEnum.name());
        }
    }

    @Test
    public void typeEnumTest(){
        String typeName = "f5";
        TypeEnum typeEnum = TypeEnum.fromTypeName(typeName);
        // typeEnum:是TypeEnum类实例化对象             typeName：实例化对象type的值
        // ordinal：实例化对象type的序号（int）         排序值(默认自带的属性 ordinal 的值)
        // name：实化对象的名字（String）               枚举名称(即默认自带的属性 name 的值)
        log.info("typeEnum的toString():{}===TypeEnum的typeName属性:{}===typeEnum的序号:{}===typeEnum的名称:{}",
                typeEnum,
                typeEnum.getTypeName(),
                typeEnum.ordinal(),
                typeEnum.name());
    }

    @Test
    public void seasonEnumTest(){
        //ordinal返回枚举变量的序号
        for(SeasonEnum seasonEnum: SeasonEnum.values()) {
            log.info("SeasonEnum常量===>seasonEnum.toString():{}===序号:{}===名称:{}",
                    seasonEnum,
                    seasonEnum.ordinal(),
                    seasonEnum.name());
            log.info("seasonEnum变量===>name属性:{}===>value属性:{}",
                    seasonEnum.getName(),
                    seasonEnum.getValue());
        }
    }

    @Test
    public void rgbEnumTest(){
        for(RGBEnum rgb:RGBEnum.values()) {
            log.info("rgb的toString():{}",rgb);
        }
    }


}
