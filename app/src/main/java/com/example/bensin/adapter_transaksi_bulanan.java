package com.example.bensin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class adapter_transaksi_bulanan extends RecyclerView.Adapter<adapter_transaksi_bulanan.MahasiswaViewHolder> {

    transaksi   transaksi;
    private ArrayList<model_bulanan> dataList;
    Context context;

    public adapter_transaksi_bulanan(ArrayList<model_bulanan> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_trx_bulanan, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, final int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        holder.penjualan.setText(dataList.get(position).getTotal_penjualan());
        holder.pembelian.setText(dataList.get(position).getTotal_pembelian());
        holder.tanggal.setText(dataList.get(position).getTanggal());

        Integer penjualan=Integer.valueOf(dataList.get(position).getTotal_penjualan());
        Integer pembelian=Integer.valueOf(dataList.get(position).getTotal_pembelian());

       // Integer hasil_penjualan=penjualan*9000;
       // Integer hasil_pembelian=pembelian*7650;
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