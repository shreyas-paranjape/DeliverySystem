package in.co.foodamigo.foodapp.service.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;

public class JsonConverter {

    private static Gson gson = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T serialize(String obj, Class<T> classOfT) {
        return gson.fromJson(obj, classOfT);
    }
}
