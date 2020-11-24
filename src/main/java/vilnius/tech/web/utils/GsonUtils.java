package vilnius.tech.web.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    private GsonUtils() {}
}
