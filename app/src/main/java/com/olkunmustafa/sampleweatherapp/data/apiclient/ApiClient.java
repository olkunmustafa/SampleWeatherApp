package com.olkunmustafa.sampleweatherapp.data.apiclient;


import android.support.annotation.NonNull;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.*;
import okio.Buffer;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by olkunmustafa on 04/10/2016.
 */
public class ApiClient {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static Retrofit retrofit = null;
    private static OkHttpClient client;

    /**
     * It defines init operations for that class.
     *
     * @since 0.1.6
     */
    private static void init() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor( new StethoInterceptor() );
        builder.readTimeout( 30, TimeUnit.SECONDS );
        builder.connectTimeout( 30, TimeUnit.SECONDS );

        builder.addInterceptor( new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain ) throws IOException {

                Request original = chain.request();
                Request.Builder newBuilder = original.newBuilder();

                Request request = newBuilder.method( original.method(), original.body() ).build();
                System.out.println(  "URL --->>" + request.url().toString());
                for ( String header : request.headers().names() ) {
                    System.out.println( "HEADERS --->>" + header + " : " + request.headers().get( header ) );
                }
                if ( request.body() != null ) {
                    Buffer buffer = new Buffer();
                    request.body().writeTo( buffer );
                    System.out.println("REQUEST BODY --->> " + buffer.readUtf8() );
                }

                Response response = chain.proceed( request );
                if ( response.body() != null ) {
                    String bodyString = response.body().string();
                    String responseRequest = response.request().url().toString();
                    try {
                        System.out.println( "RESPONSE --->> " + getResponseOut( responseRequest, bodyString ) );

                    } catch ( JSONException e ) {
                        System.out.println("RESPONSE --->> " + "Error while parsing string to JSONObject : " + e);

                    }
                    response = response.newBuilder().body( ResponseBody.create( response.body().contentType(), bodyString ) ).build();
                }

                return response;
            }
        } );

        client = builder.build();
    }

    /**
     * @return Creates the service accoring to suit the response
     * @since 0.1.6
     */
    public static < S > S createService( Class< S > serviceClass ) {
        return getClient().create( serviceClass );

    }

    /**
     * @return Retrofit client for API
     * @since 0.1.6
     */
    private static Retrofit getClient() {

        init();
        if ( retrofit == null ) {
            retrofit = new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                    .client( client )
                    .build();
        }
        return retrofit;

    }

    /**
     * @param responseURI  The URI value come from request
     * @param responseBody Body value that come from server
     * @return Get a output for response come from server.
     * @since 1.2.1
     */
    @NonNull
    private static String getResponseOut( String responseURI, String responseBody ) throws IOException, JSONException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( responseURI );
        stringBuilder.append( "\n" );
        stringBuilder.append( "BODY : " );
        stringBuilder.append( new JSONObject( responseBody ).toString( 1 ) );
        return stringBuilder.toString();

    }

}