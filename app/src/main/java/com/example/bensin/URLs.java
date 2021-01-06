package com.example.bensin;

public class URLs {
    private  static  final String SERVER="https://pomminiramajaksa.000webhostapp.com/";
    private static final String FINAL=SERVER;
    private static final String API_ROOT = FINAL+"Api.php?apicall=";
    public static final String GET_CHART_HARIAN = FINAL+"chart_harian.php";
    public static final String GET_CHART_BULANAN = FINAL+"chart_bulanan.php";
    public static final String GET_CHART_HARIAN_FULL = FINAL+"chart_harian_full.php";
    public static final String GET_CHART_BULANAN_FULL = FINAL+"chart_bulanan_full.php";
    public static final String URL_HAPUS = API_ROOT + "hapus";
    public static final String URL_AMBIL_TRX= API_ROOT + "ambil_data_trx";
    public static final String URL_AMBIL_TRX_BULANAN= API_ROOT + "ambil_bulanan";
    public static final String URL_POSTING= API_ROOT + "posting";
    public static final String URL_AMBIL_DATA= API_ROOT + "ambil_data";
    public static final String URL_AMBIL_JUMLAH= API_ROOT + "ambil_jumlah";
}
