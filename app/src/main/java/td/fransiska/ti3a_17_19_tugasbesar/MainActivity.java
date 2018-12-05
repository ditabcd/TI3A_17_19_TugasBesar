package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import td.fransiska.ti3a_17_19_tugasbesar.Adapters.ClickListener;
import td.fransiska.ti3a_17_19_tugasbesar.Adapters.RecycleTouchListener;
import td.fransiska.ti3a_17_19_tugasbesar.Adapters.TiketAdapter;
import td.fransiska.ti3a_17_19_tugasbesar.Models.TiketModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<TiketModel> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dataset.add(new TiketModel("Blitar","14.00", "Rp 50.000", R.drawable.ic_launcher_background));

        mAdapter = new TiketAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecycleTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);

                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {
            case R.id.menuGetPembelian:
                mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.menuGetPembeli:
                mIntent = new Intent(this, GetPembeliActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.menuTransaksiPembelian:
//                mIntent = new Intent(this, Main2Activity.class);
//                startActivity(mIntent);
                return true;
            case R.id.menuTransaksiPembeli:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
