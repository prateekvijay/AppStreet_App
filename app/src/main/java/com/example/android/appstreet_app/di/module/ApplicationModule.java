package com.example.android.appstreet_app.di.module;

import android.content.Context;

import com.example.android.appstreet_app.api.ApiEndPoint;
import com.example.android.appstreet_app.api.AppConstants;
import com.example.android.appstreet_app.di.scope.AppScope;
import com.example.android.appstreet_app.utils.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class ApplicationModule {


    @AppScope
    @Provides
    Retrofit provideRetrofit(Gson gson,OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @AppScope
    @Provides
    Gson provideGson() {
        return new Gson();
    }


    @AppScope
    @Provides
    ApiEndPoint provideRepoApi(Retrofit retrofit) {
        return retrofit.create(ApiEndPoint.class);
    }

    @Provides
    OkHttpClient provideOkHttpClient(Cache cache, Interceptor interceptor) {
        return new OkHttpClient.Builder().cache(cache).addNetworkInterceptor(interceptor).build();
    }

    @Provides
    Cache provideCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return cache;
    }


    @Provides
    Interceptor providerInterceptor(Context context) {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                if (Utils.isNetworkAvailable(context)) {
                    int maxAge = 60; // read from cache for 1 minute
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };
        return cacheInterceptor;
    }

}
