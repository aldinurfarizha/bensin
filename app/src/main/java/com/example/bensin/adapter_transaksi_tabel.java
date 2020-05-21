package com.example.bensin;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adapter_transaksi_tabel extends RecyclerView.Adapter<adapter_transaksi_tabel.MahasiswaViewHolder> {

    transaksi   transaksi;
    private ArrayList<model_transaksi> dataList;
    Context context;

    public adapter_transaksi_tabel(ArrayList<model_transaksi> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_trx_tabel, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, final int position) {


        holder.stand_meter.setText(dataList.get(position).getStand_meter());
        holder.penjualan.setText(dataList.get(position).getPenjualan());
        holder.pembelian.setText(dataList.get(position).getPembelian());
        holder.stock.setText(dataList.get(position).getStock());
        holder.tanggal.setText(dataList.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView stand_meter, penjualan,pembelian,stock,tanggal;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            stand_meter=(TextView) itemView.findViewById(R.id.stand_meter);
            penjualan=(TextView)itemView.findViewById(R.id.penjualan);
            pembelian=(TextView)itemView.findViewById(R.id.pembelian);
            stock=(TextView)itemView.findViewById(R.id.stock);
            tanggal=(TextView)itemView.findViewById(R.id.tanggal2);

        }
    }
}