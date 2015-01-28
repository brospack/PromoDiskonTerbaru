package com.zelory.promodiskonterbaru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by zetbaitsu on 28/01/2015.
 */
public class PromoAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Promo> promoArrayList;

    public PromoAdapter(Context context, ArrayList<Promo> promoArrayList)
    {
        this.context = context;
        this.promoArrayList = promoArrayList;
    }

    @Override
    public int getCount()
    {
        return promoArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return promoArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View itemView;
        ViewHolder holder;
        Promo promo = promoArrayList.get(position);
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item_list_layout, parent, false);
            holder = new ViewHolder((ImageView) itemView.findViewById(R.id.gambar), (TextView) itemView.findViewById(R.id.judul), (TextView) itemView.findViewById(R.id.deskripsi), (TextView) itemView.findViewById(R.id.tanggal));
            itemView.setTag(holder);
        }
        else
        {
            itemView = convertView;
            holder = (ViewHolder) itemView.getTag();
        }

        Picasso.with(context).load(promo.getGambar()).error(R.mipmap.ic_launcher).into(holder.gambar);
        holder.judul.setText(promo.getJudul());
        holder.deskripsi.setText(promo.getDeskrispi());
        holder.tanggal.setText(promo.getTanggal());

        return itemView;
    }

    private class ViewHolder
    {
        private ImageView gambar;
        private TextView judul;
        private TextView deskripsi;
        private TextView tanggal;

        private ViewHolder(ImageView gambar, TextView judul, TextView deskripsi, TextView tanggal)
        {
            this.gambar = gambar;
            this.judul = judul;
            this.deskripsi = deskripsi;
            this.tanggal = tanggal;
        }

    }
}
