package com.val.techberries.utils.dataBase.dataBaseRepository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.val.techberries.entities.ItemToUserCart;
import com.val.techberries.utils.dataBase.DAO.ItemToUserCartDao;
import com.val.techberries.utils.dataBase.MyDataBase;

import java.util.List;

public class UserCartRepository {

    private ItemToUserCartDao itemDao;
    private LiveData<List<ItemToUserCart>> allItems;

    public UserCartRepository(Application application) {
        MyDataBase myDataBase=MyDataBase.getInstance(application);
        itemDao= myDataBase.itemDao();
        allItems=itemDao.getAllItemsInCart();
    }

    public LiveData<List<ItemToUserCart>> getAllItems() {
        return allItems;
    }
    public void deleteItemFromCart(ItemToUserCart item){
        new DeleteItemAsyncTask(itemDao).execute(item);
    }
    public  void insertItemToCart(ItemToUserCart item){
        new InsertItemAsyncTask(itemDao).execute(item);
    }

    public class  DeleteItemAsyncTask extends AsyncTask<ItemToUserCart, Void,Void> {

        private  ItemToUserCartDao itemDao;

        public DeleteItemAsyncTask(ItemToUserCartDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(ItemToUserCart... itemToUserCarts) {
            itemDao.delete(itemToUserCarts[0]);
            return null;
        }
    }

    public class  InsertItemAsyncTask extends AsyncTask<ItemToUserCart, Void,Void> {

        private  ItemToUserCartDao itemDao;

        public InsertItemAsyncTask(ItemToUserCartDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(ItemToUserCart... itemToUserCarts) {
            itemDao.insert(itemToUserCarts[0]);
            Log.e("MyTag","Сделан insert  ещи с названием:"+ itemToUserCarts[0].getItemName());
            return null;
        }
    }
}
