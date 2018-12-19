package td.fransiska.ti3a_17_19_tugasbesar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

import td.fransiska.ti3a_17_19_tugasbesar.Helpers.SessionManagement;
import td.fransiska.ti3a_17_19_tugasbesar.Models.ResultTiket;
import td.fransiska.ti3a_17_19_tugasbesar.Models.Tiket;
import td.fransiska.ti3a_17_19_tugasbesar.Rest.ApiClient;
import td.fransiska.ti3a_17_19_tugasbesar.Rest.ApiInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import td.fransiska.ti3a_17_19_tugasbesar.Rest.ApiInterface;

public class PutDelTiketActivity extends AppCompatActivity {

    EditText edtKota, edtWaktu, edtHarga;
    Button btnAddGambar, btnTakePicture, btnAddTiket;
    ImageView imgGambar;

    String imagePath = "";
    private static final int REQUEST_IMAGE_PICTURE = 101;

    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_del_tiket);

        edtKota = findViewById(R.id.edtKota);
        edtWaktu = findViewById(R.id.edtWaktu);
        edtHarga = findViewById(R.id.edtHarga);
        btnAddGambar = findViewById(R.id.btnAddGambar);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnAddTiket = findViewById(R.id.btnAddTiket);
        imgGambar = findViewById(R.id.imgGambar);

        sessionManagement = new SessionManagement(this);


        btnAddTiket.setText("Update");
        Intent mIntent = getIntent();
        final Tiket mTiket = (Tiket) mIntent.getSerializableExtra("extraTiket");

        edtKota.setText(mTiket.getKota());
        edtWaktu.setText(mTiket.getWaktu());
        edtHarga.setText(String.valueOf(mTiket.getHarga()));

        Picasso.with(getApplicationContext()).load(mTiket.getPhotoId()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imgGambar);

        btnAddGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih Gambar Untuk Di upload");
                startActivityForResult(intentChoose, 10);
            }
        });

        btnAddTiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    File file = new File(imagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body = MultipartBody.Part.createFormData("id_photo", file.getName(), requestFile);
                }
                RequestBody regIdTiket = MultipartBody.create(MediaType.parse("multipart/form-data"), String.valueOf(mTiket.getIdTiket()));
                RequestBody regKota = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtKota.getText().toString().isEmpty())?"":edtKota.getText().toString());
                RequestBody regWaktu = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtWaktu.getText().toString().isEmpty())?"":edtWaktu.getText().toString());
                RequestBody regHarga = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtHarga.getText().toString().isEmpty())?"":edtHarga.getText().toString());
                Call<ResultTiket> mPembeliCall = mApiInterface.putTiket(body,regIdTiket,regKota,regWaktu, regHarga);
                mPembeliCall.enqueue(new Callback<ResultTiket>() {
                    @Override
                    public void onResponse(Call<ResultTiket> call, Response<ResultTiket> response) {
                        Log.d("Insert Retrofit",response.body().getStatus());
                        Toast.makeText(PutDelTiketActivity.this,":"+response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<ResultTiket> call, Throwable t) {
                        Log.d("Insert Retrofit", t.getMessage());
                        Toast.makeText(PutDelTiketActivity.this,":"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(PutDelTiketActivity.this, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath =cursor.getString(columnIndex);

                Glide.with(PutDelTiketActivity.this).load(new File(imagePath)).into(imgGambar);
                cursor.close();
            }else{
                Toast.makeText(PutDelTiketActivity.this, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == REQUEST_IMAGE_PICTURE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imgGambar.setImageBitmap(imageBitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));

            imagePath = getRealPathFromURI(tempUri);
        }
    }
    public void takePicture(View view){
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getPackageManager())  != null ){
            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_PICTURE);
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
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
            case R.id.menuAddTiket:
                Intent i = new Intent(getApplicationContext(),AddTiketActivity.class);
                startActivity(i);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
