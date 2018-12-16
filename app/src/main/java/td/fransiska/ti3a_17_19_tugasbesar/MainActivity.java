package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import td.fransiska.ti3a_17_19_tugasbesar.Adapters.ClickListener;
import td.fransiska.ti3a_17_19_tugasbesar.Adapters.RecycleTouchListener;
import td.fransiska.ti3a_17_19_tugasbesar.Adapters.TiketAdapter;
import td.fransiska.ti3a_17_19_tugasbesar.Helpers.SessionManagement;
import td.fransiska.ti3a_17_19_tugasbesar.Models.ResultTiket;
import td.fransiska.ti3a_17_19_tugasbesar.Models.Tiket;
import td.fransiska.ti3a_17_19_tugasbesar.Rest.ApiClient;
import td.fransiska.ti3a_17_19_tugasbesar.Rest.ApiInterface;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Tiket> dataset = new ArrayList<>();
    private SessionManagement sessionManagement;

    EditText edtSearch;
    Button btnSearch;
    List<Tiket> listPembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManagement = new SessionManagement(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);


        mAdapter = new TiketAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);

        getTiket();

        mRecyclerView.addOnItemTouchListener(new RecycleTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("tiket_data",listPembeli.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kata = edtSearch.getText().toString();
                getTiketbyKota(kata);
            }
        });
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
            case R.id.menuLogout:
                sessionManagement.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void getTiket()
    {
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultTiket> mPembeliCall = mApiInterface.getTiket();
        mPembeliCall.enqueue(new Callback<ResultTiket>() {
            @Override
            public void onResponse(Call<ResultTiket> call,
                                   Response<ResultTiket> response) {
                Log.d("Get Pembeli",response.body().getStatus());
                listPembeli = response.body().getResult();
                mAdapter = new TiketAdapter(listPembeli, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<ResultTiket> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
            }
        });
    }

    public void getTiketbyKota(String kata)
    {
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultTiket> mPembeliCall = mApiInterface.getTiketbyKota(kata);
        mPembeliCall.enqueue(new Callback<ResultTiket>() {
            @Override
            public void onResponse(Call<ResultTiket> call,
                                   Response<ResultTiket> response) {
                Log.d("Get Pembeli",response.body().getStatus());
                listPembeli = response.body().getResult();
                if (listPembeli.size()==0)
                {
                    listPembeli.add(new Tiket(0,"Not Found","Not Found",404,"https://idevice.co.id/data/avatars/o/6/6817.jpg",""));
                }
                mAdapter = new TiketAdapter(listPembeli, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<ResultTiket> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
            }
        });
    }
}
