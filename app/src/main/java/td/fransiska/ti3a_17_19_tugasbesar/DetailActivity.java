package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import td.fransiska.ti3a_17_19_tugasbesar.Helpers.SessionManagement;
import td.fransiska.ti3a_17_19_tugasbesar.Models.Tiket;

public class DetailActivity extends AppCompatActivity {

    Button btnPembayaran;
    Button btnMaps;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnPembayaran = findViewById(R.id.btnPembayaran);
        btnMaps = findViewById(R.id.btnMaps);

        Intent mIntent = getIntent();
        sessionManagement = new SessionManagement(this);

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
