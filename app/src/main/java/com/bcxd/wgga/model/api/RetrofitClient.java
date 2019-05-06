package com.bcxd.wgga.model.api;

import android.content.Context;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.model.bean.LoginInfo;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jinxh on 16/2/17.
 */
public class RetrofitClient {
    private static Retrofit mRetrofit;

    private RetrofitClient(Context context) {

    }

    public static Retrofit builderRetrofit(final Context context) {
        if (mRetrofit == null) {
            // log
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            //添加head
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {

                    Gson gson = new Gson();
//                    String s = AppContext.dbHelper.GetValue(DbKeyS.logininfo);
                    String s = SpUtils.getSettingNote(context, DbKeyS.logininfo);
//                    LoginInfo loginInfo = gson.fromJson(s, LoginInfo.class);

//                    String Bearer = "Bearer ";
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder();

                    Response proceed = chain.proceed(requestBuilder.build());
//                    if (loginInfo != null) {
////                        String token = AppContext.dbHelper.GetValue(DbKeyS.token);
//                        String token = SpUtils.getSettingNote(context, DbKeyS.token);
//                        if (token.equals("-1")) {
//                            requestBuilder.addHeader("Authorization", Bearer + loginInfo.getRefresh_token());
//                        } else {
//                            requestBuilder.addHeader("Authorization", Bearer + loginInfo.getAccess_token());
//                        }
//                    }
                    String token = SpUtils.getSettingNote(context, DbKeyS.token);
                    requestBuilder.addHeader("Content-Type", "application/json");
                    requestBuilder.addHeader("VERSION", "1.0");
                    requestBuilder.addHeader("Accept", "application/json");
                    if (token != null)
                        requestBuilder.addHeader("token", token);
//                    Request.Builder requestBuilder = original.newBuilder()
//                            .addHeader("Authorization", Authorization + loginInfo.getAccess_token())
//                            .addHeader("Content-Type", "application/json")
//                            .addHeader("VERSION", "1.0")
//                            .addHeader("Accept", "application/json");

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            httpClient.addInterceptor(logging);
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullToDefaultValueAdapterFactory()).create();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(AppContext.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();

        }
        return mRetrofit;
    }

    public static class NullToDefaultValueAdapterFactory<T> implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType == String.class) {
                return (TypeAdapter<T>) new StringNullAdapter();
            } else if (rawType == Boolean.class) {
                return (TypeAdapter<T>) new BooleanNullAdapter();
            } else if (rawType == Integer.class) {
                return (TypeAdapter<T>) new IntegerNullAdapter();
            } else if (rawType == Double.class) {
                return (TypeAdapter<T>) new DoubleNullAdapter();
            } else if (rawType == Long.class) {
                return (TypeAdapter<T>) new LongNullAdapter();
            }
            return null;
        }
    }

    public static class LongNullAdapter extends TypeAdapter<Long> {
        @Override
        public Long read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0l;
            }
            return reader.nextLong();
        }

        @Override
        public void write(JsonWriter writer, Long value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class DoubleNullAdapter extends TypeAdapter<Double> {
        @Override
        public Double read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0.0d;
            }
            double doubleValue = 0.0d;
            try {
                String stringValue = reader.nextString();
                doubleValue = Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                return 0.0d;
            }
            return doubleValue;
        }

        @Override
        public void write(JsonWriter writer, Double value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class IntegerNullAdapter extends TypeAdapter<Integer> {
        @Override
        public Integer read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0;
            }
            return reader.nextInt();
        }

        @Override
        public void write(JsonWriter writer, Integer value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class BooleanNullAdapter extends TypeAdapter<Boolean> {
        @Override
        public Boolean read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return false;
            }
            return reader.nextBoolean();
        }

        @Override
        public void write(JsonWriter writer, Boolean value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class StringNullAdapter extends TypeAdapter<String> {
        @Override
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }
}
