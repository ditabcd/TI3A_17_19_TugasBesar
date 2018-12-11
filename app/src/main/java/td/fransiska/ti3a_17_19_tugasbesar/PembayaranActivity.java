package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import td.fransiska.ti3a_17_19_tugasbesar.Models.ResultPembelian;
import td.fransiska.ti3a_17_19_tugasbesar.Rest.ApiInterface;
import td.fransiska.ti3a_17_19_tugasbesar.Rest.ApiClient;

public class PembayaranActivity extends AppCompatActivity {

    ImageView imgGambar;
    TextView txtIdTiket;
    TextView edtTanggal;
    TextView edtNama;
    TextView edtAlamatJemput;
    TextView edtAlamatAntar;

    Button btnAddGambar;
    Button btnAddPembayaran;

    String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        txtIdTiket = findViewById(R.id.txtIdTiket);
        imgGambar = findViewById(R.id.imgGambar);
        edtTanggal = findViewById(R.id.edtTanggal);
        edtNama = findViewById(R.id.edtNama);
        edtAlamatJemput = findViewById(R.id.edtAlamatJemput);
        edtAlamatAntar = findViewById(R.id.edtAlamatAntar);
        btnAddPembayaran = findViewById(R.id.btnAddPembayaran);

        final Intent mIntent = getIntent();

        txtIdTiket.setText(String.valueOf(mIntent.getStringExtra("id_tiket")));


        btnAddPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                RequestBody regIdTiket = MultipartBody.create(MediaType.parse("multipart/form-data"), mIntent.getStringExtra("id_tiket"));
                RequestBody regTanggal = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtTanggal.getText().toString().isEmpty())?"":edtTanggal.getText().toString());
                RequestBody regNama = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtNama.getText().toString().isEmpty())?"":edtNama.getText().toString());
                RequestBody regAlamatJemput = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtAlamatJemput.getText().toString().isEmpty())?"":edtAlamatJemput.getText().toString());
                RequestBody regAlamatAntar = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtAlamatAntar.getText().toString().isEmpty())?"":edtAlamatAntar.getText().toString());
                Call<ResultPembelian> mPembeliCall = mApiInterface.postPembelian(regIdTiket,regTanggal,regNama,regAlamatJemput,regAlamatAntar);
                mPembeliCall.enqueue(new Callback<ResultPembelian>() {
                    @Override
                    public void onResponse(Call<ResultPembelian> call, Response<ResultPembelian> response) {
                        Log.d("Insert Retrofit",response.body().getStatus());
                        Toast.makeText(PembayaranActivity.this,":"+response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<ResultPembelian> call, Throwable t) {
                        Log.d("Insert Retrofit", t.getMessage());
                        Toast.makeText(PembayaranActivity.this,":"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
