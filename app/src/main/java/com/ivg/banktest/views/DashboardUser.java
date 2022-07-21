package com.ivg.banktest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ivg.banktest.R;
import com.ivg.banktest.controllers.AdapterBalance;
import com.ivg.banktest.controllers.AdapterCards;
import com.ivg.banktest.controllers.AdapterMovimientos;
import com.ivg.banktest.models.cardTransactions;
import com.ivg.banktest.models.cardsUsers;
import com.ivg.banktest.models.userBalance;
import com.ivg.banktest.views.MyToolbar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardUser extends AppCompatActivity {

    TextView userLastConect;
    TextView btnAddCards;
    private RequestQueue queue;
    RecyclerView balanceRecycler;
    RecyclerView CardsRecycler;
    RecyclerView CardsMovRecycler;
    List<userBalance> ListBalance;
    List<cardsUsers> ListCards;
    AlertDialog mdialog;
    List<cardTransactions> ListCardsMov;
    private AdapterBalance adapterBalances;
    private AdapterCards adapterCards;
    private AdapterMovimientos adapterCardsMov;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager2;
    StaggeredGridLayoutManager staggeredGridLayoutManager3;
    public static final String URL_USER = "http://bankapp.endcom.mx/api/bankappTest/cuenta";
    public static final String URL_SALDOS = "http://bankapp.endcom.mx/api/bankappTest/saldos";
    public static final String URL_CARDS = "http://bankapp.endcom.mx/api/bankappTest/tarjetas";
    public static final String URL_CARDS_MOV = "http://bankapp.endcom.mx/api/bankappTest/movimientos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

        userLastConect = findViewById(R.id.userConnection);
        btnAddCards = findViewById(R.id.lv_add_card);
        balanceRecycler = findViewById(R.id.rv_balances);
        CardsRecycler = findViewById(R.id.rv_cards);
        CardsMovRecycler = findViewById(R.id.rv_movimientos);

        balanceRecycler.setHasFixedSize(true);
        balanceRecycler.setLayoutManager(new LinearLayoutManager(this));

        CardsRecycler.setHasFixedSize(true);
        CardsRecycler.setLayoutManager(new LinearLayoutManager(this));

        CardsMovRecycler.setHasFixedSize(true);
        CardsMovRecycler.setLayoutManager(new LinearLayoutManager(this));


        //initializing the productlist
        ListBalance = new ArrayList<>();
        ListCards = new ArrayList<>();
        ListCardsMov = new ArrayList<>();
        queue = Volley.newRequestQueue(this);



        viewLastConnect();
        viewBalances();
        viewCards();
        viewCardsMov();


        btnAddCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardUser.this, AddNewCard.class);
                startActivity(i);
            }
        });


    }

    private void viewCardsMov() {

        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.GET,
                URL_CARDS_MOV,null, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject s) {

                try {
                    JSONArray array =s.getJSONArray("movimientos");

                    for (int i=0; i<array.length();i++){

                        JSONObject object=array.getJSONObject(i);

                        cardTransactions item=new cardTransactions(

                                object.getString("fecha"),
                                object.getString("descripcion"),
                                object.getString("monto"),
                                object.getString("tipo"));

                        ListCardsMov.add(item);

                    }

                    adapterCardsMov =new AdapterMovimientos(getApplicationContext(), ListCardsMov);
                    CardsMovRecycler.setAdapter(adapterCardsMov);


                    staggeredGridLayoutManager3 = new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL);
                    CardsMovRecycler.setLayoutManager (staggeredGridLayoutManager3);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this) ;
        requestQueue.add(stringRequest);



    }

    private void viewCards() {

        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.GET,
                URL_CARDS,null, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject s) {

                try {
                    JSONArray array =s.getJSONArray("tarjetas");

                    for (int i=0; i<array.length();i++){

                        JSONObject object=array.getJSONObject(i);

                        cardsUsers item=new cardsUsers(

                                object.getString("tarjeta"),
                                object.getString("nombre"),
                                object.getString("saldo"),
                                object.getString("estado"),
                                object.getString("tipo"));

                        ListCards.add(item);

                    }

                    adapterCards =new AdapterCards(getApplicationContext(), ListCards);
                    CardsRecycler.setAdapter(adapterCards);


                    staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL);
                    CardsRecycler.setLayoutManager (staggeredGridLayoutManager2);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this) ;
        requestQueue.add(stringRequest);




    }

    private void viewBalances() {

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Cargando cuenta espere un momento..");
        progressDialog.show();


        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.GET,
                URL_SALDOS,null, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject s) {
                progressDialog.dismiss();
                try {


                    JSONArray array =s.getJSONArray("saldos");

                    for (int i=0; i<array.length();i++){

                        JSONObject object=array.getJSONObject(i);

                        userBalance item=new userBalance(

                                object.getString("saldoGeneral"),
                                object.getString("ingresos"),
                                object.getString("gastos"));

                        ListBalance.add(item);

                    }

                    adapterBalances =new AdapterBalance(getApplicationContext(), ListBalance);
                    balanceRecycler.setAdapter(adapterBalances);


                    staggeredGridLayoutManager = new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL);
                    balanceRecycler.setLayoutManager (staggeredGridLayoutManager);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this) ;
        requestQueue.add(stringRequest);


    }

    private void viewLastConnect() {

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Cargando Proveedores espere un momento...");
        progressDialog.show();

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_USER ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("cuenta");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String name=jsonObject1.getString("nombre");
                                String lastcnn=jsonObject1.getString("ultimaSesion");
                                userLastConect.setText(name +" "+ lastcnn);
                            }

                        }catch (JSONException e){
                            progressDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                error.printStackTrace();

            }
        });
        RequestQueue requestQueues = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}