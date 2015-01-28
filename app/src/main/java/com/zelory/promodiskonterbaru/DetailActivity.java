package com.zelory.promodiskonterbaru;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView gambarBesar = (ImageView) findViewById(R.id.gambarBesar);
        TextView judul = (TextView) findViewById(R.id.judul);
        TextView deskripsi = (TextView) findViewById(R.id.deskripsi);
        TextView tanggal = (TextView) findViewById(R.id.tanggal);
        Promo promo = (Promo) getIntent().getSerializableExtra(MainActivity.SER_KEY);

        Picasso.with(DetailActivity.this).load(promo.getGambarBesar()).error(R.mipmap.ic_launcher).into(gambarBesar);
        judul.setText(promo.getJudul());
        deskripsi.setText(promo.getDeskrispi());
        tanggal.setText(promo.getTanggal());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }
        else if (id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
