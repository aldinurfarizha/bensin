package com.example.bensin;


import android.app.Activity;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class adapter_transaksi extends RecyclerView.Adapter<adapter_transaksi.MahasiswaViewHolder> {

    transaksi   transaksi;
    private ArrayList<model_transaksi> dataList;
    Context context;

    public adapter_transaksi(ArrayList<model_transaksi> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_trx, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, final int position) {


        holder.stand_meter.setText(dataList.get(position).getStand_meter());
        holder.penjualan.setText(dataList.get(position).getPenjualan());
        holder.pembelian.setText(dataList.get(position).getPembelian());
        holder.stock.setText(dataList.get(position).getStock());
        holder.tanggal.setText(dataList.get(position).getTanggal());
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getRootView().getContext());

                // set title dialog
                alertDialogBuilder.setTitle("Hapus");

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Anda Yakin Menghapus Transaksi Ini ?")
                        .setIcon(R.drawable.hapus)
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                              final String id_transaksi=dataList.get(position).getId_transaksi();
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_HAPUS,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject obj = new JSONObject(response);
                                                    if (!obj.getBoolean("error")) {

                                                    } else {

                                                        Toast.makeText(view.getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(view.getContext(), "Data Gagal di Hapus Karena Koneksi Rabig, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                                            }
                                        }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("id_transaksi", id_transaksi);

                                        return params;
                                    }
                                };

                                VolleySingleton.getInstance(view.getRootView().getContext()).addToRequestQueue(stringRequest);

                                Toast.makeText(view.getContext(), "Data Berhasil di Hapus :)", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView stand_meter, penjualan,pembelian,stock,tanggal;
        private ImageView hapus;
        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            stand_meter=(TextView) itemView.findViewById(R.id.stand_meter);
            penjualan=(TextView)itemView.findViewById(R.id.penjualan);
            pembelian=(TextView)itemView.findViewById(R.id.pembelian);
            stock=(TextView)itemView.findViewById(R.id.stock);
            tanggal=(TextView)itemView.findViewById(R.id.tanggal2);
            hapus=(ImageView) itemView.findViewById(R.id.btn_hapus);

        }
    }
}