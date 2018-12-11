package td.fransiska.ti3a_17_19_tugasbesar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import td.fransiska.ti3a_17_19_tugasbesar.Models.Tiket;
import td.fransiska.ti3a_17_19_tugasbesar.R;

public class TiketAdapter extends RecyclerView.Adapter<TiketAdapter.CustomHolder> {

    private List<Tiket> dataset;
    Context mContext;

    public TiketAdapter(List<Tiket> dataset, Context mContext) {
        this.dataset = dataset;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_menu, viewGroup, false);
        CustomHolder viewHolder = new CustomHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder customHolder, int i) {
        Tiket tm = dataset.get(i);
        customHolder.judul.setText(tm.getKota());
        customHolder.waktuBerangkat.setText(tm.getWaktu());
        customHolder.harga.setText(String.valueOf(tm.getHarga()));
        Picasso.with(mContext).load(tm.getPhotoId()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(customHolder.foto);

        //customHolder.foto.setImageResource(tm.getPhotoId());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class CustomHolder extends RecyclerView.ViewHolder{
        TextView judul, waktuBerangkat, harga;
        ImageView foto;
        View listItem;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.txtJudul);
            waktuBerangkat = itemView.findViewById(R.id.txtWaktuBerangkat);
            harga = itemView.findViewById(R.id.txtHarga);
            foto = itemView.findViewById(R.id.imgKota);
            listItem = itemView;
        }
    }

    }

