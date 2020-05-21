package com.example.bensin;

public class model_bulanan {
    private String total_penjualan;
    private String total_pembelian;
    private String tanggal;

    public model_bulanan(String total_penjualan, String total_pembelian, String tanggal) {
        this.total_penjualan = total_penjualan;
        this.total_pembelian = total_pembelian;
        this.tanggal = tanggal;
    }

    public String getTotal_penjualan() {
        return total_penjualan;
    }

    public void setTotal_penjualan(String total_penjualan) {
        this.total_penjualan = total_penjualan;
    }

    public String getTotal_pembelian() {
        return total_pembelian;
    }

    public void setTotal_pembelian(String total_pembelian) {
        this.total_pembelian = total_pembelian;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
