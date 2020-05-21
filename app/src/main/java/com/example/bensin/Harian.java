package com.example.bensin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Harian extends AppCompatActivity {
    private ArrayList<model_transaksi> model_transaksiList;
    adapter_transaksi_harian adapter;
    RecyclerView gridView;
    ImageView refresh;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harian);
        model_transaksiList = new ArrayList<>();
        gridView=(RecyclerView)findViewById(R.id.list_trx_harian);
        refresh=(ImageView)findViewById(R.id.refresh);
        progressBar=(ProgressBar)findViewById(R.id.progressbar_transaksi);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model_transaksiList.clear();
                getData();
            }
        });
        getData();
    }

    private void getData() {
        StringRequest stringRequest =new StringRequest(Request.Method.GET, URLs.URL_AMBIL_TRX, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);
                        model_transaksi modelTransaksi=new model_transaksi(ob.getString("id_transaksi"),ob.getString("stand_meter"), ob.getString("pembelian"), ob.getString("penjualan"), ob.getString("stock"), ob.getString("tanggal"));
                        model_transaksiList.add(modelTransaksi);
                    }
                    progressBar.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                    adapter=new adapter_transaksi_harian(model_transaksiList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Harian.this, LinearLayoutManager.VERTICAL,false);
                    gridView.setLayoutManager(layoutManager);
                    gridView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Harian.this, "Koneksi Error,Tunggu Sebentar", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 3000);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    } //ambil list maknan
}