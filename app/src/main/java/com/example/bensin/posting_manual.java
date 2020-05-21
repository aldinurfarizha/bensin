package com.example.bensin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class posting_manual extends AppCompatActivity {
EditText stand_meter, penjualan, pembelian, stock, tanggal;
Button btn_simpan, btn_batal;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_manual);
        stand_meter=(EditText)findViewById(R.id.stand_meter_input2);
        penjualan=(EditText)findViewById(R.id.penjualan_input2);
        pembelian=(EditText)findViewById(R.id.pembelian_input2);
        stock=(EditText)findViewById(R.id.stock_input2);
        tanggal=(EditText)findViewById(R.id.tanggal2);
        btn_simpan=(Button)findViewById(R.id.btn_simpan2);
        btn_batal=(Button)findViewById(R.id.btn_batal2);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
showDateDialog();
            }
        });
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ip_meter = stand_meter.getText().toString().trim();
                final String ip_pembelian = pembelian.getText().toString().trim();
                final String ip_tanggal = tanggal.getText().toString().trim();
                final String ip_penjualan = penjualan.getText().toString().trim();
                final String ip_stock = stock.getText().toString().trim();
                if (TextUtils.isEmpty(ip_meter)) {
                    stand_meter.setError("Masukan Angka Standmeter");
                    stand_meter.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ip_pembelian)) {
                    pembelian.setError("Masukan Angka Pembelian");
                    pembelian.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ip_penjualan)) {
                    penjualan.setError("Masukan Angka Penjualan");
                    penjualan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ip_stock)) {
                    stock.setError("Masukan Angka Stock");
                    stock.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ip_tanggal)) {
                    tanggal.setError("Masukan Angka stock");
                    tanggal.requestFocus();
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
                                        finish();
                                        Intent intent = new Intent(posting_manual.this, MainActivity.class);
                                        startActivity(intent);

                                    } else {

                                        Toast.makeText(getApplicationContext(), obj.getString("message")+" Ulangi Posting", Toast.LENGTH_SHORT).show();
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

                VolleySingleton.getInstance(posting_manual.this).addToRequestQueue(stringRequest);
            }
        });


    }
    public void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tanggal.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
