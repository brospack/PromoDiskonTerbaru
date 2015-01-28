package com.zelory.promodiskonterbaru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener
{
    private ListView listView;
    private ArrayList<Promo> promoArrayList;
    private PromoAdapter promoAdapter;
    public final static String SER_KEY = "com.zelory.promodiskonterbaru.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        new DownloadData().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SER_KEY, promoArrayList.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class DownloadData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            promoArrayList = new ArrayList<>();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIcon(R.mipmap.ic_launcher);
            progressDialog.setTitle("Promo Diskon Terbaru");
            progressDialog.setMessage("Mendownload data...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            for (int i = 0; i <= 90; i += 10)
            {
                Document document = null;
                try
                {
                    document = Jsoup.connect("http://www.serbapromosi.co/food-and-beverages?start=" + i).timeout(10000).get();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (document != null)
                {
                    Elements elements = document.select(Promo.TAG_JUDUL);
                    for (int j = 0; j < elements.size(); j++)
                    {
                        Element element = elements.get(j);
                        promoArrayList.add(new Promo());
                        promoArrayList.get(j + i).setJudul(element.text());
                    }

                    elements = document.select(Promo.TAG_GAMBAR);
                    for (int j = 0; j < elements.size(); j++)
                    {
                        Element element = elements.get(j);
                        String gambar = element.attr("src");
                        String regex = "(jpg|jpeg|png|gif|bmp)";
                        Matcher matcher = Pattern.compile(regex).matcher(gambar);
                        if (matcher.find())
                            gambar = gambar.substring(0, matcher.end());

                        String gambarBesar = "http://www.serbapromosi.co/" + gambar.substring(gambar.indexOf("images/"));
                        promoArrayList.get(j + i).setGambar(gambar);
                        promoArrayList.get(j + i).setGambarBesar(gambarBesar);
                    }

                    elements = document.select(Promo.TAG_DESKRIPSI);
                    for (int j = 0; j < elements.size(); j++)
                    {
                        Element element = elements.get(j);
                        promoArrayList.get(j + i).setDeskrispi(element.text());
                    }
                    elements = document.select(Promo.TAG_TANGGAL);
                    for (int j = 0; j < elements.size(); j++)
                    {
                        Element element = elements.get(j);
                        String tanggal = element.text();
                        tanggal = tanggal.substring(0, tanggal.lastIndexOf("Penulis"));
                        promoArrayList.get(j + i).setTanggal(tanggal);
                        progressDialog.setProgress(j + i);
                    }
                }
                else
                {
                    i -= 10;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            promoAdapter = new PromoAdapter(MainActivity.this, promoArrayList);
            SwingRightInAnimationAdapter swingRightInAnimationAdapter = new SwingRightInAnimationAdapter(promoAdapter);
            swingRightInAnimationAdapter.setAbsListView(listView);
            listView.setAdapter(swingRightInAnimationAdapter);
            progressDialog.setProgress(100);
            progressDialog.dismiss();
        }
    }
}
