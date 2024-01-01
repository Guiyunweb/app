package plus.guiyun.app.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

/**
 * JPA避免一些空值对于动态部分更新的影响工具类
 * (null值不更新, 配合实体类@DynamicUpdate使用)
 */
public class JpaUtil {

    /**
     * 查询出entity值 -> JpaUtil.copyNotNullProperties(input, entity); -> save(entity)
     *
     * @param input  输入实体类
     * @param entity 数据库查询出的实体类
     */
    public static void copyNotNullProperties(Object input, Object entity) {
        BeanUtils.copyProperties(input, entity, getNullPropertyNames(input));
    }

    /**
     * 忽略的字段：值为null、浮点数为0.0、值为空字符串""
     * <p>
     * ignoreProperties {@link BeanUtils#copyProperties(java.lang.Object, java.lang.Object, java.lang.String...)}
     *
     * @param object 传入全部字段
     * @return 忽略的字段
     */
    private static String[] getNullPropertyNames(Object object) {
        final BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
        return Stream.of(wrapper.getPropertyDescriptors()).map(PropertyDescriptor::getName)
                .filter(propertyName -> wrapper.getPropertyValue(propertyName) == null
                        || "0.0".equals(String.valueOf(wrapper.getPropertyValue(propertyName)))
                        || "".equals(String.valueOf(wrapper.getPropertyValue(propertyName))))
                .toArray(String[]::new);
    }

}
