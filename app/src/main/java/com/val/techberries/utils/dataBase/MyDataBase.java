package com.val.techberries.utils.dataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.val.techberries.entities.ItemToUserCart;
import com.val.techberries.utils.dataBase.DAO.ItemToUserCartDao;


@Database(entities = {ItemToUserCart.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {
    private  static MyDataBase instance;

    public  abstract ItemToUserCartDao itemDao();

    public  static synchronized MyDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                    , MyDataBase.class,"MyDataBase1")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private  static Callback roomCallBack=new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new fillNoteTableWithData(instance).execute();
        }
    };
    //заполним данными нашу таблицу
    private  static class  fillNoteTableWithData extends AsyncTask<Void,Void,Void>{
        private ItemToUserCartDao itemDao;

        public fillNoteTableWithData(MyDataBase dataBase) {
            this.itemDao = dataBase.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
