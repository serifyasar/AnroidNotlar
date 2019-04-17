package com.serifyasargmail.fixnfix;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    public ListView listView;
    ArrayList<Repair> repairs;
    String url="http://fixnfix.co.uk/api/repair";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();



            listView= (ListView) findViewById(R.id.liste);

            queue= Volley.newRequestQueue(getApplicationContext());
            veriAl();





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    Repair customer=repairs.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("customer", customer);
                    Intent intent = new Intent(getApplicationContext(), Detay.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                catch (Exception e){

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });





    }




    private void veriAl() {

        //Öncelikle Volley içerisinden bir sorgu oluşturmasını istiyoruz.
        // Ve bu sorgu hangi context içerisinde kullanılacak bilgisini içerisinde gösteriyoruz.
        queue = Volley.newRequestQueue(MainActivity.this);
        //Verileri tutacağımız arraylistimizi oluşturduk.
        repairs = new ArrayList<>();
        //Sorgunun gerçekleştiğinde yapacağı işlemleri yapalım.
        JsonObjectRequest getYaziRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray repairList= response.getJSONArray("repairList");
                            for (int i = 0; i < repairList.length(); i++){
                                JSONObject repairsuct=repairList.getJSONObject(i);
                                int id=Integer.valueOf(repairsuct.getString("id"));
                                String name=repairsuct.getString("name") + " " + repairsuct.getString("surname");
                                String email=repairsuct.getString("email");
                                String company=repairsuct.getString("company");
                                String city=repairsuct.getString("city");
                                String address=repairsuct.getString("address");
                                String phone=repairsuct.getString("phone");
                                String country=repairsuct.getString("country");
                                String postcode=repairsuct.getString("postcode");
                                String message=repairsuct.getString("message");
                                String repair=repairsuct.getString("repair");
                                String device=repairsuct.getString("device");
                                String cost=repairsuct.getString("cost");
                                String type=repairsuct.getString("type");

                                repairs.add(new Repair(id,name,email,company,address,city,country,phone,postcode,message,device,repair,cost,type));



                            }

                            RepairAdapter  repairsuctAdapter = new RepairAdapter(getApplicationContext(),R.layout.listview_layout_temp,repairs);

                            ListView listviewrepairsuct = (ListView) findViewById(R.id.liste);
                            if(listviewrepairsuct != null){
                                listviewrepairsuct.setAdapter(repairsuctAdapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                        Toast.makeText(MainActivity.this,"ppp"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        //Sorgu kuyruğuna gerçekleştirilmek üzere sorgumuzu veriyoruz.
        //Ve request işşlemi başlıyor.
        queue.add(getYaziRequest);

    }
}
