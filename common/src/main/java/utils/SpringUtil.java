package utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 */
public class SpringUtil {
    private static HashMap<String, Object> objectMap = new LinkedHashMap<String, Object>();

    public static ClassPathXmlApplicationContext context = null;

    public static ClassPathXmlApplicationContext getContext() {
        if (null == context) {
            String[] xmlContext = new String[]{"/applicationContext.xml", "/ocr-config.xml"};
            context = new ClassPathXmlApplicationContext(xmlContext);
//            context.refresh();
            context.start();
        }
        return context;
    }

    public static Object getObject(String id) {
        if (!objectMap.containsKey(id)) {
            Object object = null;
            object = getContext().getBean(id);
            objectMap.put(id, object);
        }
        return objectMap.get(id);
    }
}
