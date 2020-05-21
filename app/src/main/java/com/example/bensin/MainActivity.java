package com.example.bensin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    WebView chart_harian, chart_bulanan;
    ProgressBar progress_harian, progress_bulanan, p1, p2, p3, p4, p5, p6, p7, p8;
    TextView tanggal, stand_meter_tv, penjualan_tv, pembelian_tv, stock_tv, p_penjualan, p_pembelian, p_laba, pendapatan_tv, full_harian, full_bulanan;
    String tanggals, tanggals2, tanggal_dipilih;
    Integer pembelian, pembelian2, penjualan, penjualan2, harga_beli = 7650, harga_jual = 9000, hasil_pembelian, hasil_penjualan, hasil_laba, pendapatan, stand_mtr, stock_mtr;
    Double hasil_pembelian_rp, hasil_penjualan_rp, laba_rp, pendapatan_rp;
    LinearLayout btn_posting, btn_penjualan, btn_pembelian, btn_transaksi;
    EditText tanggal_input, stand_meter_input, pembelian_input, edt_cekstock, edt_beli;
    Button cek_stock, posting_manual, table;
    private DatePickerDialog datePickerDialog;
    private ArrayList<model> models;
    private ArrayList<model2> models2;
    private SimpleDateFormat dateFormatter;
    boolean a=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posting_manual=(Button)findViewById(R.id.posting_manual);
        table=(Button)findViewById(R.id.tabel);
        models = new ArrayList<>();
        models2 = new ArrayList<>();
        edt_cekstock = (EditText) findViewById(R.id.edt_cekstock);
        edt_beli = (EditText) findViewById(R.id.edt_cekbeli);
        cek_stock = (Button) findViewById(R.id.cek_stock);
        chart_harian = (WebView) findViewById(R.id.grafik_harian);
        chart_bulanan = (WebView) findViewById(R.id.grafik_bulanan);
        progress_harian = (ProgressBar) findViewById(R.id.progres_harian);
        progress_bulanan = (ProgressBar) findViewById(R.id.progres_bulanan);
        tanggal = (TextView) findViewById(R.id.tanggal);
        stand_meter_tv = (TextView) findViewById(R.id.stand_meter_result);
        penjualan_tv = (TextView) findViewById(R.id.penjualan_result);
        pembelian_tv = (TextView) findViewById(R.id.pembelian_result);
        stock_tv = (TextView) findViewById(R.id.stock_result);
        p_penjualan = (TextView) findViewById(R.id.p_penjualan);
        p_pembelian = (TextView) findViewById(R.id.p_pembelian);
        p_laba = (TextView) findViewById(R.id.p_laba);
        pendapatan_tv = (TextView) findViewById(R.id.pendapatan);
        btn_posting = (LinearLayout) findViewById(R.id.btn_posting);
        btn_pembelian = (LinearLayout) findViewById(R.id.btn_pembelian);
        btn_penjualan = (LinearLayout) findViewById(R.id.btn_penjualan);
        btn_transaksi = (LinearLayout) findViewById(R.id.btn_transaksi);
        p1 = (ProgressBar) findViewById(R.id.progres1);
        p2 = (ProgressBar) findViewById(R.id.progres2);
        p3 = (ProgressBar) findViewById(R.id.progres3);
        p4 = (ProgressBar) findViewById(R.id.progres4);
        p5 = (ProgressBar) findViewById(R.id.progres5);
        p6 = (ProgressBar) findViewById(R.id.progres6);
        p7 = (ProgressBar) findViewById(R.id.progres7);
        p8 = (ProgressBar) findViewById(R.id.progres8);
        full_harian = (TextView) findViewById(R.id.btn_30_hari);
        full_bulanan = (TextView) findViewById(R.id.btn_1_tahun);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        //chart
        chart_bulanan.setWebViewClient(new myWebclient());
        chart_bulanan.getSettings().setJavaScriptEnabled(true);
        chart_bulanan.loadUrl(URLs.GET_CHART_BULANAN);
        chart_harian.setWebViewClient(new myWebclient());
        chart_harian.getSettings().setJavaScriptEnabled(true);
        chart_harian.loadUrl(URLs.GET_CHART_HARIAN);
        //chart

        ///POSTING
        final LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formsView = inflater.inflate(R.layout.posting, null, false);
        stand_meter_input = (EditText) formsView.findViewById(R.id.stand_meter_input);
        pembelian_input = (EditText) formsView.findViewById(R.id.pembelian_input);
        tanggal_input = (EditText) formsView.findViewById(R.id.tanggal);
        final Button btn_simpan = (Button) formsView.findViewById(R.id.btn_simpan);
        final Button btn_batal = (Button) formsView.findViewById(R.id.btn_batal);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setView(formsView).setCancelable(false);
        final AlertDialog alertDialog = dialog.create();
        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ip_meter = stand_meter_input.getText().toString().trim();
                final String ip_pembelian = pembelian_input.getText().toString().trim();
                final String ip_tanggal = tanggal_input.getText().toString().trim();
                Integer penjualan2 = Integer.valueOf(ip_meter) - stand_mtr;
                Integer stock2 = stock_mtr + Integer.valueOf(ip_pembelian) - penjualan2;
                final String ip_penjualan = Integer.toString(penjualan2);
                final String ip_stock = Integer.toString(stock2);
                if (TextUtils.isEmpty(ip_meter)) {
                    stand_meter_input.setError("Masukan Angka Standmeter");
                    stand_meter_input.requestFocus();
                    return;
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_POSTING,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    if (!obj.getBoolean("error")) {
                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                        models.clear();
                                        models2.clear();
                                        getData();
                                        getData2();
                                        alertDialog.dismiss();
                                    } else {

                                        Toast.makeText(getApplicationContext(), obj.getString("message")+"Ulangi Posting", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Gagal Koneksi Buruk, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("stand_meter", ip_meter);
                        params.put("pembelian", ip_pembelian);
                        params.put("penjualan", ip_penjualan);
                        params.put("stock", ip_stock);
                        params.put("tanggal", ip_tanggal);

                        return params;
                    }
                };

                VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);


            }
        });
        ////akhir posting///

        tanggal_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        tanggals2 = formatter2.format(today);
        tanggal_input.setText(tanggals2);
        getTanggal();
        getData();
        cek_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cek_stock();
            }
        });
        full_harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, full_harian.class);
                startActivity(intent);
            }
        });
        full_bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, full_bulanan.class);
                startActivity(intent);
            }
        });
        btn_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Harian.class);
                startActivity(intent);
            }
        });
        btn_pembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bulanan.class);
                startActivity(intent);
            }
        });
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, transaksi_tabel.class);
                startActivity(intent);
            }
        });
        posting_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.bensin.posting_manual.class);
                startActivity(intent);
            }
        });
        btn_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, transaksi.class);
                startActivity(intent);
            }
        });
        btn_posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!a){
                    alertDialog.show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Aplikasi Belum siap atau tidak ada transaksi di bulan Ini.slihakan input manual", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progress_harian.setVisibility(View.GONE);
            progress_bulanan.setVisibility(View.GONE);
            chart_bulanan.setVisibility(View.VISIBLE);
            chart_harian.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(MainActivity.this, "Koneksi Error,Tunggu Sebentar", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    chart_bulanan.setWebViewClient(new myWebclient());
                    chart_bulanan.getSettings().setJavaScriptEnabled(true);
                    chart_bulanan.loadUrl(URLs.GET_CHART_BULANAN);
                    chart_harian.setWebViewClient(new myWebclient());
                    chart_harian.getSettings().setJavaScriptEnabled(true);
                    chart_harian.loadUrl(URLs.GET_CHART_HARIAN);
                }
            }, 3000);

        }
    }

    private void getTanggal() {
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");//formating according to my need
        tanggals = formatter.format(today);
        tanggal.setText(tanggals);

    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_AMBIL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);
                        model listData = new model(ob.getString("id_transaksi"), ob.getString("stand_meter"), ob.getString("pembelian"), ob.getString("penjualan"), ob.getString("stock"));
                        models.add(listData);
                    }
                    p1.setVisibility(View.GONE);
                    p2.setVisibility(View.GONE);
                    p3.setVisibility(View.GONE);
                    p4.setVisibility(View.GONE);
                    p5.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);
                    p7.setVisibility(View.GONE);


                    stand_meter_tv.setVisibility(View.VISIBLE);

                    stock_tv.setVisibility(View.VISIBLE);
                    pembelian_tv.setVisibility(View.VISIBLE);
                    penjualan_tv.setVisibility(View.VISIBLE);
                    p_pembelian.setVisibility(View.VISIBLE);
                    p_laba.setVisibility(View.VISIBLE);
                    p_penjualan.setVisibility(View.VISIBLE);

                    if(models!=null && models.size()>0){
                        a=false;
                        stand_mtr = Integer.valueOf(models.get(0).getStand_meter());
                        stock_mtr = Integer.valueOf(models.get(0).getStock());
                        stand_meter_tv.setText(models.get(0).getStand_meter());
                        penjualan_tv.setText(models.get(0).getPenjualan());
                        pembelian_tv.setText(models.get(0).getPembelian());
                        stock_tv.setText(models.get(0).getStock());

                        pembelian = Integer.parseInt(models.get(0).getPembelian());
                        penjualan = Integer.parseInt(models.get(0).getPenjualan());

                        hasil_penjualan = penjualan * harga_jual;
                        hasil_pembelian = pembelian * harga_beli;
                        hasil_laba = hasil_penjualan - hasil_pembelian;

                        laba_rp = Double.valueOf(hasil_laba);
                        hasil_pembelian_rp = Double.valueOf(hasil_pembelian);
                        hasil_penjualan_rp = Double.valueOf(hasil_penjualan);
                        Locale localeID = new Locale("in", "ID");
                        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                        p_penjualan.setText(formatRupiah.format(hasil_penjualan_rp));
                        p_pembelian.setText(formatRupiah.format(hasil_pembelian_rp));
                        p_laba.setText(formatRupiah.format(laba_rp));
                        getData2();

                    }
                    else {
                        a=true;
                        Toast.makeText(MainActivity.this, "Belum Ada Transaksi di Bulan Ini", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Koneksi Error,Tunggu Sebentar", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 3000);

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    } //ambil data terakhir

    private void getData2() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_AMBIL_JUMLAH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);
                        model2 listData2 = new model2(ob.getString("total_penjualan"), ob.getString("total_pembelian"));
                        models2.add(listData2);
                    }
                    p8.setVisibility(View.GONE);
                    pendapatan_tv.setVisibility(View.VISIBLE);

                    pembelian2 = Integer.parseInt(models2.get(0).getTotal_pembelian());
                    penjualan2 = Integer.parseInt(models2.get(0).getTotal_penjualan());
                   // Integer j = penjualan2 * harga_jual;
                   // Integer b = pembelian2 * harga_beli;
                    pendapatan =penjualan2 * 1350;


                    pendapatan_rp = Double.valueOf(pendapatan);
                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                    pendapatan_tv.setText(formatRupiah.format(pendapatan_rp));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Koneksi Error,Tunggu Sebentar", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData2();
                    }
                }, 3000);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    } //ambil jumlah

    public void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tanggal_input.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public void cek_stock() {
        final String cek_stand = edt_cekstock.getText().toString().trim();
        if (TextUtils.isEmpty(cek_stand)) {
            edt_cekstock.setError("Masukan Angka Standmeter");
            edt_cekstock.requestFocus();
            return;
        }
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        Integer stand_now=Integer.valueOf(cek_stand);
        Integer stand_check=Integer.valueOf(stand_meter_tv.getText().toString());
        Integer stock_check=Integer.valueOf(stock_tv.getText().toString());
        Integer beli_check=Integer.valueOf(edt_beli.getText().toString());

        Integer hasil_stock= (stock_check+beli_check)-(stand_now-stand_check);
        Integer hasil_jual= stand_now-stand_check;
        Integer hasil_jual_harga= hasil_jual*9000;
        Double rupiah_jual= Double.valueOf(hasil_jual_harga);




        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Cek Stock")
                .setMessage("Stock Saat Ini adalah "+hasil_stock+" Litter"+"\nTotal Terjual "+hasil_jual+" Litter"+"\n Pendapatan :"+formatRupiah.format(rupiah_jual))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(R.drawable.standmeter)
                .show();

    }
}
