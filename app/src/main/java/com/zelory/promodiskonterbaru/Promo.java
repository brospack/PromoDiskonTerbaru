package com.zelory.promodiskonterbaru;

import java.io.Serializable;

/**
 * Created by zetbaitsu on 28/01/2015.
 */
public class Promo implements Serializable
{
    private static final long serialVersionUID = -7060210544600464481L;
    public final static String TAG_JUDUL = "td.contentheading_news";
    public final static String TAG_GAMBAR = "img.timthumb";
    public final static String TAG_DESKRIPSI = "div.list-thumb";
    public final static String TAG_TANGGAL = "span.small";
    
    private String judul;
    private String gambar;
    private String deskrispi;
    private String tanggal;
    private String gambarBesar;

    public String getJudul()
    {
        return judul;
    }

    public void setJudul(String judul)
    {
        this.judul = judul;
    }

    public String getGambar()
    {
        return gambar;
    }

    public void setGambar(String gambar)
    {
        this.gambar = gambar;
    }

    public String getDeskrispi()
    {
        return deskrispi;
    }

    public void setDeskrispi(String deskrispi)
    {
        this.deskrispi = deskrispi;
    }

    public String getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(String tanggal)
    {
        this.tanggal = tanggal;
    }

    public String getGambarBesar()
    {
        return gambarBesar;
    }

    public void setGambarBesar(String gambarBesar)
    {
        this.gambarBesar = gambarBesar;
    }
}
