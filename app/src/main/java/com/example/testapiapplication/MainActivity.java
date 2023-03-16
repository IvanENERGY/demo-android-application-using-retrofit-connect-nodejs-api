package com.example.testapiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Methods methods;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRes=findViewById(R.id.tvRes);

        methods=RetrofitClient.getRetrofitInstance().create(Methods.class); //retrofit create the implementation of methods

       // ListOutAllOrders(); //get
       // ListSpecificOrder(); //get
     //  createOrder(); //post
        //updateOrder();  //put/patch
       // deleteOrder();
    }



    private void ListOutAllOrders() {
        Call<List<Order>> call= methods.getOrders(null)  ;
        call.enqueue(new Callback<List<Order>>() {//execute the call and get the response;network op. need to be run in background thread
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(!response.isSuccessful()) {
                    //not 200-300
                    tvRes.setText("ResponseCode: "+response.code());
                    return;
                }
                List<Order> orders=response.body();
                for(Order o:orders){
                    String content = "";
                    content+="id: "+o.getId()+'\n';
                    content+="title: "+o.getTitle()+'\n';
                    content+="Qty: "+o.getTitle()+'\n';
                    content+="Message: "+o.getMessage()+'\n';
                    content+="City: "+o.getCity()+"\n\n";

                    tvRes.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                tvRes.setText(t.getMessage());
            }
        });

    }
    private void ListSpecificOrder(){
        Call<List<Order>> call= methods.getOrder(2)  ;
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(!response.isSuccessful()) {
                    //not 200-300
                    tvRes.setText("ResponseCode: "+response.code());
                    return;
                }
                List<Order> orders=response.body();
                if(!orders.isEmpty()) {
                    Order o = orders.get(0);
                    String content = "";
                    content += "id: " + o.getId() + "\n";
                    content += "title: " + o.getTitle() + "\n";
                    content += "qty: " + o.getQuantity() + "\n";
                    content += "message: " + o.getMessage() + "\n";
                    content += "city: " + o.getCity() + "\n\n";
                    tvRes.append(content);
                }
                else{
                    tvRes.append("nth fetched");
                }

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                tvRes.setText(t.getMessage());
            }
        });


    }
    private void createOrder() {
  //      Order order = new Order("9988","GfriendCD",1,"On sales","Las Vegas");
//        Map<String,String> fields= new HashMap<>();
//        fields.put("Id","1234");
//        fields.put("Title","Book");
//        fields.put("Quantity","3");
//        fields.put("Message","Test Msg");
//        fields.put("City","Paris");


        Call<Order> call = methods.createOrder("9911","BfriendCD",1,"On sales","Las Vegas");
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(!response.isSuccessful()){
                    tvRes.setText("ResCode:"+response.code());
                    return;
                }
                Order postResponse=response.body();
                String content = "";
                content+= "Code: "+response.code()+"\n";
                content += "id: " + postResponse.getId() + "\n";
                content += "title: " + postResponse.getTitle() + "\n";
                content += "qty: " + postResponse.getQuantity() + "\n";
                content += "message: " + postResponse.getMessage() + "\n";
                content += "city: " + postResponse.getCity() + "\n\n";
                tvRes.append(content);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                tvRes.setText("failure: "+t.getMessage());
            }
        });


    }
    public void updateOrder(){
        // Order o= new Order(null,"testTitle",3,"TestMSG","Vienna");
        // Call<Order> call = methods.putOrder(1234,o);
        Order o= new Order(null,"title",2,"hi","BankKok");

        Map<String,String> headerMap=new HashMap<>();
        headerMap.put("mapHeader1","abcd");
        headerMap.put("mapHeader2","ddd");

        Call<Order> call = methods.patchOrder(headerMap,188,o);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(!response.isSuccessful()){
                    tvRes.setText("ResCode:"+response.code());
                    return;
                }
                Order postResponse=response.body();
                String content = "";
                content+= "Code: "+response.code()+"\n";
                content += "id: " + postResponse.getId() + "\n";
                content += "title: " + postResponse.getTitle() + "\n";
                content += "qty: " + postResponse.getQuantity() + "\n";
                content += "message: " + postResponse.getMessage() + "\n";
                content += "city: " + postResponse.getCity() + "\n\n";
                tvRes.append(content);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                tvRes.setText("failure: "+t.getMessage());
            }
        });



    }
    public void deleteOrder(){


       Call<Void>call= methods.deleteOrder(6195);
       call.enqueue(new Callback<Void>() {
           @Override
           public void onResponse(Call<Void> call, Response<Void> response) {
               tvRes.setText("response Code:" + response.code());
           }

           @Override
           public void onFailure(Call<Void> call, Throwable t) {
               tvRes.setText("fail:"+t.getMessage());
           }
       });




    }
}