package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import td.fransiska.ti3a_17_19_tugasbesar.Helpers.SessionManagement;
import td.fransiska.ti3a_17_19_tugasbesar.Models.Tiket;

public class DetailActivity extends AppCompatActivity {

    TextView txtKota, txtHargaTravel, txtWaktu;
    ImageView  imgKota;
    Button btnPembayaran;
    Button btnMaps;
    Button btnUpdate;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtKota = findViewById(R.id.txtKota);
        txtHargaTravel = findViewById(R.id.txtHarga);
        txtWaktu = findViewById(R.id.txtWaktu);
        imgKota = findViewById(R.id.imgKota);

        btnPembayaran = findViewById(R.id.btnPembayaran);
        btnMaps = findViewById(R.id.btnMaps);
        btnUpdate= findViewById(R.id.btnUpdate);

        Intent mIntent = getIntent();
        sessionManagement = new SessionManagement(this);

        final Tiket mTiket = (Tiket) mIntent.getSerializableExtra("tiket_data");

        txtKota.setText(mTiket.getKota());
        txtHargaTravel.setText(String.valueOf(mTiket.getHarga()));
        txtWaktu.setText(mTiket.getWaktu());
        Picasso.with(getApplicationContext()).load(mTiket.getPhotoId()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imgKota);

        btnPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this,PembayaranActivity.class);
                i.putExtra("id_tiket",String.valueOf(mTiket.getIdTiket()));
                startActivity(i);
            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this,PutDelTiketActivity.class);
                i.putExtra("extraTiket", mTiket);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {
            case R.id.menuLogout:
                sessionManagement.logoutUser();
                return true;
            case R.id.menuAddTiket:
                Intent i = new Intent(getApplicationContext(),AddTiketActivity.class);
                startActivity(i);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
