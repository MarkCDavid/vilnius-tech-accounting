package vilnius.tech.web.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vilnius.tech.web.utils.adapters.HibernateProxyTypeAdapter;

public class GsonUtils {

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    private GsonUtils() {}
}
