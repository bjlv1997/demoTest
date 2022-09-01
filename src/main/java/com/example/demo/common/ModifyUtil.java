package com.example.demo.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/9/16 14:54
 */
public class ModifyUtil {
    public static final String UPDATE = "UPDATE";
    public static final String CREATE = "CREATE";
    public static final String DELETE = "DELETE";
    private static final String NAME_FRE_FIX = "set";
    private static final String CHANGE_FLAG = "Flag";
    private static final String OBJECT_FLAG = "objectFlag";
    public static String[] ignoreProperties = new String[]{"objectVersionNumber", "creationDate", "createdBy", "lastUpdatedBy", "lastUpdateDate"};

    public ModifyUtil() {
    }

    public static void classOfSrcResult(Object source, Object target) {
        Map<String, String> resultMap = new HashMap();
        List<String> sourceKeys = new ArrayList();
        Arrays.asList(source.getClass().getDeclaredFields()).forEach((item) -> {
            sourceKeys.add(item.getName());
        });
        List<String> targetKeys = new ArrayList();
        Arrays.asList(target.getClass().getDeclaredFields()).forEach((item) -> {
            targetKeys.add(item.getName());
        });
        List<String> sameKey = new ArrayList();
        Iterator var6 = sourceKeys.iterator();

        while(var6.hasNext()) {
            String key = (String)var6.next();
            if (targetKeys.contains(key)) {
                sameKey.add(key);
            }
        }

        sameKey.forEach((item) -> {
            String srcValue = getClassValue(source, item) == null ? "" : getClassValue(source, item).toString();
            String tarValue = getClassValue(target, item) == null ? "" : getClassValue(target, item).toString();
            if (!srcValue.equals(tarValue)) {
                resultMap.put(item + "Flag", "UPDATE");
            }

        });
        mapConvertBean(resultMap, source);
        mapConvertBean(resultMap, target);
    }

    private static Object getClassValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        } else {
            try {
                Class<? extends Object> beanClass = obj.getClass();
                Method[] ms = beanClass.getMethods();
                Method[] var4 = ms;
                int var5 = ms.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Method m = var4[var6];
                    if (m.getName().startsWith("get")) {
                        Object objValue;
                        try {
                            objValue = m.invoke(obj);
                        } catch (Exception var10) {
                            continue;
                        }

                        if (objValue != null && (m.getName().toUpperCase().equals(fieldName.toUpperCase()) || m.getName().substring(3).toUpperCase().equals(fieldName.toUpperCase()))) {
                            return objValue;
                        }
                    }
                }

                return null;
            } catch (Exception var11) {
                return null;
            }
        }
    }

    private static void mapConvertBean(Map<String, String> map, Object obj) {
        Class clazz = obj.getClass();
        boolean object = false;
        if (!map.isEmpty()) {
            Iterator var4 = map.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<String, String> keyValue = (Map.Entry)var4.next();
                Field field = getClassField(clazz, (String)keyValue.getKey());
                if (field != null) {
                    try {
                        Method method = clazz.getMethod("set".concat(((String)keyValue.getKey()).substring(0, 1).toUpperCase().concat(((String)keyValue.getKey()).substring(1))), field.getType());
                        method.invoke(obj, keyValue.getValue());
                        object = true;
                    } catch (Exception var9) {
                        var9.printStackTrace();
                    }
                }
            }

            if (object) {
                Field field = getClassField(clazz, "objectFlag");
                if (field != null) {
                    try {
                        Method method = clazz.getMethod("set".concat("objectFlag".substring(0, 1).toUpperCase().concat("objectFlag".substring(1))), field.getType());
                        method.invoke(obj, "UPDATE");
                    } catch (Exception var8) {
                        var8.printStackTrace();
                    }
                }
            }
        }

    }

    private static Field getClassField(Class<?> clazz, String fieldName) {
        if (clazz != null && !Object.class.getName().equals(clazz.getName())) {
            Field[] declaredFields = clazz.getDeclaredFields();
            Field[] var3 = declaredFields;
            int var4 = declaredFields.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Field field = var3[var5];
                if (field.getName().equals(fieldName)) {
                    return field;
                }
            }

            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getClassField(superClass, fieldName);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Set<String> compareField(Object source, Object target, String... ignoreProperties) {
        Set<String> changeFields = new HashSet();
        List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        List<String> sourceKeys = new ArrayList();
        Arrays.asList(source.getClass().getDeclaredFields()).forEach((item) -> {
            sourceKeys.add(item.getName());
        });
        List<String> targetKeys = new ArrayList();
        Arrays.asList(target.getClass().getDeclaredFields()).forEach((item) -> {
            targetKeys.add(item.getName());
        });
        List<String> sameKey = new ArrayList();
        Iterator var8 = sourceKeys.iterator();

        while(var8.hasNext()) {
            String key = (String)var8.next();
            if (targetKeys.contains(key)) {
                sameKey.add(key);
            }
        }

        sameKey.forEach((item) -> {
            if (ignoreList == null || !ignoreList.contains(item)) {
                String srcValue = getClassValue(source, item) == null ? "" : getClassValue(source, item).toString();
                String tarValue = getClassValue(target, item) == null ? "" : getClassValue(target, item).toString();
                if (!srcValue.equals(tarValue)) {
                    changeFields.add(item);
                }
            }

        });
        return changeFields;
    }
}
