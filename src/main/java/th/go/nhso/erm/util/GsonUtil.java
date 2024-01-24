package th.go.nhso.erm.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class GsonUtil {

    public static String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(ThrowableAdapterFactory.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .setDateFormat(YYYYMMDD_HHMMSS).create();
        return gson.toJson(obj);
    }

    public static String toJson(List<Object> objList) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(ThrowableAdapterFactory.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .setDateFormat(YYYYMMDD_HHMMSS).create();
        return gson.toJson(objList);
    }

    public static List fromJsonList(String jsonStr, Type type){
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(ThrowableAdapterFactory.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .setDateFormat(YYYYMMDD_HHMMSS).create();
        return gson.fromJson(jsonStr, type);
    }

    public static Object fromJsonObject(String jsonStr, Type type){
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(ThrowableAdapterFactory.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .setDateFormat(YYYYMMDD_HHMMSS).create();
        return gson.fromJson(jsonStr, type);
    }

}
