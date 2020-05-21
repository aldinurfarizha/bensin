package com.example.bensin;

public class model2 {
    private String total_penjualan;
    private String total_pembelian;

    public model2(String total_penjualan, String total_pembelian) {
        this.total_penjualan = total_penjualan;
        this.total_pembelian = total_pembelian;
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
}
