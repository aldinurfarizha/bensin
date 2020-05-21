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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class adapter_transaksi_harian extends RecyclerView.Adapter<adapter_transaksi_harian.MahasiswaViewHolder> {

    transaksi   transaksi;
    private ArrayList<model_transaksi> dataList;
    Context context;

    public adapter_transaksi_harian(ArrayList<model_transaksi> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_trx_harian, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, final int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        holder.penjualan.setText(dataList.get(position).getPenjualan());
        holder.pembelian.setText(dataList.get(position).getPembelian());
        holder.tanggal.setText(dataList.get(position).getTanggal());

        Integer penjualan=Integer.valueOf(dataList.get(position).getPenjualan());
        Integer pembelian=Integer.valueOf(dataList.get(position).getPembelian());

        //Integer hasil_penjualan=penjualan*9000;
        //Integer hasil_pembelian=pembelian*7650;
        Integer pendapatan=penjualan*1350;
        Double pendapatan_d=Double.valueOf(pendapatan);
        holder.pendapatan.setText(formatRupiah.format(pendapatan_d));
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView pendapatan, penjualan,pembelian,tanggal;
        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            penjualan=(TextView)itemView.findViewById(R.id.penjualan);
            pembelian=(TextView)itemView.findViewById(R.id.pembelian);
            tanggal=(TextView)itemView.findViewById(R.id.tanggal2);
            pendapatan=(TextView)itemView.findViewById(R.id.penghasilan);

        }
    }
}