package com.serifyasargmail.cevsen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME="cevsen";
    private static final String TABLE_NAME="dualar";
    private static final int DB_VER=2;

    public Database(Context context) {
        super(context, DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+ TABLE_NAME +
                " (_id integer primary key autoincrement," +
                "dua text, sayfa integer)";
        db.execSQL(sql);
        db.execSQL("insert into "+ TABLE_NAME + "(dua,sayfa) values('cevsen',0)");
        db.execSQL("insert into "+ TABLE_NAME + "(dua,sayfa) values('fetih',0)");
        db.execSQL("insert into "+ TABLE_NAME + "(dua,sayfa) values('yasin',0)");
        db.execSQL("insert into "+ TABLE_NAME + "(dua,sayfa) values('abedir',0)");
        db.execSQL("insert into "+ TABLE_NAME + "(dua,sayfa) values('sabah',0)");
        db.execSQL("insert into "+ TABLE_NAME + "(dua,sayfa) values('aksam',0)");
        db.execSQL("insert into "+ TABLE_NAME + "(dua,sayfa) values('tevhid',0)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public long kayitEkle(String dua,int sy) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("dua",dua);
        cv.put("sayfa",0);
        long durum=db.insert(TABLE_NAME,null,cv);

        return durum;
    }

/*
    public ArrayList<Nobetler> listeAl(int ay) {
        ArrayList<Nobetler> liste = new ArrayList<Nobetler>();


         SQLiteDatabase db = getReadableDatabase();
         Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where ay="+ay+" order by gun", null);

         int cnt = c.getColumnIndex("tarih");
         int cnb = c.getColumnIndex("bilgi");
         int cng = c.getColumnIndex("gun");
         int cna = c.getColumnIndex("ay");
         int cny = c.getColumnIndex("yil");
         int cns = c.getColumnIndex("sgun");
         for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
             Nobetler nobet = new Nobetler(c.getString(cnb), c.getString(cnt),c.getInt(cng), c.getInt(cna),c.getInt(cny),c.getString(cns));
             liste.add(nobet);
         }


         c.close();
         db.close();

         return liste;



    }

    public void veriSil(String s) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME, " tarih like '" + s + "'", null);

    }

*/
    public void durumKaydet(String dua, int sayfa) {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("update " + TABLE_NAME + " set dua='"+dua+"', sayfa="+sayfa+" where dua like '" + dua + "' ");



    }

    public int sayfaAl(String dua){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where dua like '"+dua+"' ", null);

        int csayfa = c.getColumnIndex("sayfa");
        c.moveToFirst();
        int syf=c.getInt(csayfa);
        c.close();
        return  syf;
    }
}
