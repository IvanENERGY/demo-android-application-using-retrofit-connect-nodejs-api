package com.example.testapiapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Methods {
    //if u write("/orders"), last part of base url will be replace:
   // base - >http://192.168.102.123:8090/api/
   // become -> http://192.168.102.123:8090/orders

    @GET("orders") //u can do "http://192.168.102.123:8090/api/orders" overwrite whole base url
    Call<List<Order>> getOrders(
            @Query("id") Integer orderId  //int is not nullable //@Query("id") Integer[] id, for multiple query
            //,... for multiple query
    );
//use @QueryMap("params") Map<String,String> parameters for long query //key is res.query, and corres. value
    @GET("orders/{id}")
    Call<List<Order>> getOrder(@Path("id") int orderId); //path is use for dynamic replacement

    @GET
    Call<List<Order>> getOrder(@Url String url); //same as above

    @POST("orders")
    Call<Order> createOrder(@Body Order order);

    @FormUrlEncoded //same as above
    @POST("orders")
    Call<Order> createOrder(
        @Field("Id") String id,
        @Field("Title") String title,
        @Field("Quantity") int qty,
        @Field("Message") String msg,
        @Field("City") String city
    );

    @FormUrlEncoded //same as above
    @POST("orders")
    Call<Order> createOrder(@FieldMap Map<String,String> fields);

    @Headers({"Static-Header: 123","Static-Header2: 456"})
    @PUT("orders/{id}")
    Call<Order> putOrder(@Header("Dynamic-Header") String header,
                         @Path("id") int id,
                         @Body Order order);

    @PATCH("orders/{id}")
    Call<Order> patchOrder(@HeaderMap Map<String,String> headers,
                           @Path("id") int id,
                           @Body Order order);

    @DELETE("orders/{id}")
    Call<Void> deleteOrder(@Path("id") int id);
}
