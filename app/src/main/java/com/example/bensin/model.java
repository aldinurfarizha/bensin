package com.example.bensin;

public class model {
    private String id_transaksi;
    private String stand_meter;
    private String pembelian;
    private String penjualan;
    private String stock;

    public model(String id_transaksi, String stand_meter, String pembelian, String penjualan, String stock) {
        this.id_transaksi = id_transaksi;
        this.stand_meter = stand_meter;
        this.pembelian = pembelian;
        this.penjualan = penjualan;
        this.stock = stock;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getStand_meter() {
        return stand_meter;
    }

    public void setStand_meter(String stand_meter) {
        this.stand_meter = stand_meter;
    }

    public String getPembelian() {
        return pembelian;
    }

    public void setPembelian(String pembelian) {
        this.pembelian = pembelian;
    }

    public String getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(String penjualan) {
        this.penjualan = penjualan;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
