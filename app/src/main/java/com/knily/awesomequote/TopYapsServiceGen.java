package com.knily.awesomequote;

import android.util.Log;

import com.knily.awesomequote.helper.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

public class TopYapsServiceGen{

    private static final String CACHE_CONTROL = "Cache-Control";

    private static boolean isInterceptorAdded = false;

    private static final String base_URL = "https://knily.com/"; //"http://topyaps.com/";

    private static OkHttpClient client = null;

    /*private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .addInterceptor(provideOfflineCacheInterceptor())
            .addNetworkInterceptor(provideCacheInterceptor());

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <T> T createService(Class<T> serviceClass) {
        addLoggingInterceptor(httpClient);
        Retrofit retrofit = retrofitBuilder.
                client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }

    private static void addLoggingInterceptor(OkHttpClient.Builder builder) {
        if (!isInterceptorAdded) {
            isInterceptorAdded = true;
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
    }*/
    ////////////////////////////////////////////////////////////////

    public static Retrofit provideRetrofit (String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl( baseUrl )
                .client( provideOkHttpClient() )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }

    private static OkHttpClient provideOkHttpClient ()
    {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(provideHttpLoggingInterceptor())
                    .addInterceptor(provideOfflineCacheInterceptor())
                    .addNetworkInterceptor(provideCacheInterceptor())
                    .cache(provideCache())
                    .build();
        }
        return client;
    }

    private static Cache provideCache ()
    {
        Cache cache = null;
        try
        {
            cache = new Cache( new File(Utils.getInstance().getCacheDir(), "http-cache" ), 10 * 1024 * 1024 ); // 10 MB
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cache;
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor ()
    {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger()
                {
                    @Override
                    public void log (String message)
                    {
                        Log.d("Message", message );
                    }
                } );
        httpLoggingInterceptor.setLevel( BuildConfig.DEBUG ? HEADERS : NONE );
        return httpLoggingInterceptor;
    }

    public static Interceptor provideCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept (Interceptor.Chain chain) throws IOException
            {
                Response response = chain.proceed( chain.request() );

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, TimeUnit.MINUTES).build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString() )
                        .build();
            }
        };
    }

    public static Interceptor provideOfflineCacheInterceptor ()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept (Chain chain) throws IOException
            {
                Request request = chain.request();

                if (!Utils.getInstance().checkIfHasNetwork())
                {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(2, TimeUnit.DAYS).build();

                    request = request.newBuilder()
                            .cacheControl( cacheControl )
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    public static PostRetrieve provideTopyapsService ()
    {
        return provideRetrofit(base_URL).create(PostRetrieve.class);
    }
    ////////////////////////////////////////////////////////////////////////////
}