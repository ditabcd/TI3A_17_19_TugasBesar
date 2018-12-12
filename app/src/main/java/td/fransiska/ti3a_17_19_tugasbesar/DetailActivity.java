package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import td.fransiska.ti3a_17_19_tugasbesar.Models.Tiket;

public class DetailActivity extends AppCompatActivity {

    Button btnPembayaran;
    Button btnMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnPembayaran = findViewById(R.id.btnPembayaran);
        btnMaps = findViewById(R.id.btnMaps);

        Intent mIntent = getIntent();

        final Tiket mTiket = (Tiket) mIntent.getSerializableExtra("tiket_data");

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
    }
}
