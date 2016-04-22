package com.app50knetwork.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by ashkumar on 4/16/2016.
 */
public class UserDeserializer<T> implements JsonDeserializer<T> {

    private final Class mNestedClazz;
    private final Object mNestedDeserializer;

    public UserDeserializer(Class nestedClazz, Object nestedDeserializer) {
        mNestedClazz = nestedClazz;
        mNestedDeserializer = nestedDeserializer;
    }
    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

        //JsonElement profile = je.getAsJsonObject().get("profile");
        GsonBuilder builder = new GsonBuilder();
        if (mNestedClazz != null && mNestedDeserializer != null) {
            builder.registerTypeAdapter(mNestedClazz, mNestedDeserializer);
        }
        return builder.create().fromJson(je, type);

    }
}
