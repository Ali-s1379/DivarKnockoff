package com.example.divarknockoff.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.divarknockoff.AdOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository {
private static Repository advertRepository;

    private SQLiteDatabase database;
    private AdObjectDao advertDao;
    private AccountDao accountDao;
    private Context mContext;
    private static Account currentUser;

    public static Repository getInstance(Context context) {
        if (advertRepository == null)
            advertRepository = new Repository(context);

        return advertRepository;
    }

//    private Cursor queryCrime(String where, String[] whereArgs) {
//        return database.query(AdvertDBSchema.Advert.NAME,
//                null,
//                where,
//                whereArgs,
//                null,
//                null,
//                null);
//    }

    public List<AdObject> getAdObjects() {
//        List<AdObject> adObjects = new ArrayList<>();
//
//        Cursor cursor = queryCrime(null, null);
//
//        try {
//            cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//                String strUUID = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.UUID));
//                String title = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.TITLE));
//                String phone = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.PHONE));
//                String city = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.CITY));
//                String content = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.DESCRIPTION));
//                boolean vip = cursor.getInt(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.VIP)) == 1;
//
//                AdObject adObject = AdObject.AdBuilder.anAdObject()
//                        .title(title)
//                        .content(content)
//                        .city(city)
//                        .phone(phone)
//                        .vip(vip)
//                        .id(UUID.fromString(strUUID))
//                        .build();
//                adObjects.add(adObject);
//
//                cursor.moveToNext();
//            }
//
//        } finally {
//            cursor.close();
//        }

//        return adObjects;
        return advertDao.loadAll();
    }

    public AdObject getAdObject(UUID uuid) {
//        String[] whereArgs = new String[]{uuid.toString()};
//        Cursor cursor = queryCrime(AdvertDBSchema.Advert.Cols.UUID + " = ?", whereArgs);
//
//        try {
//            if (cursor == null || cursor.getCount() == 0)
//                return null;
//
//            cursor.moveToFirst();
//
//            String strUUID = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.UUID));
//            String title = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.TITLE));
//            String phone = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.PHONE));
//            String city = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.CITY));
//            String content = cursor.getString(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.DESCRIPTION));
//            boolean vip = cursor.getInt(cursor.getColumnIndex(AdvertDBSchema.Advert.Cols.VIP)) == 1;
//
//            AdObject adObject = AdObject.AdBuilder.anAdObject()
//                    .title(title)
//                    .content(content)
//                    .city(city)
//                    .phone(phone)
//                    .vip(vip)
//                    .id(UUID.fromString(strUUID))
//                    .build();
//
//            return adObject;
//
//        } finally {
//            cursor.close();
//        }
        return advertDao.queryBuilder().where(AdObjectDao.Properties.Id.eq(uuid.toString())).unique();
    }


    public void insertAdObject(AdObject adObject) {
//        ContentValues values = getContentValues(adObject);
//        database.insertOrThrow(AdvertDBSchema.Advert.NAME, null, values);
        advertDao.insert(adObject);
    }

    public void deleteAdObject(AdObject adObject) {
//        AdObject ad = getAdObject(adObject.getId());
//        if (ad == null)
//            throw new Exception("AD Not Found");
//
//        database.delete(AdvertDBSchema.Advert.NAME, AdvertDBSchema.Advert.Cols.UUID + " = ?",new String[]{String.valueOf(ad.getId())});
        advertDao.delete(adObject);
    }
    public void updateAdObject(AdObject adObject) {
        advertDao.update(adObject);
    }
    public void deleteTable(){
//        database.execSQL("delete from "+ AdvertDBSchema.Advert.NAME);
        List<AdObject> list = getAdObjects();
        for (AdObject adObject : list) {
            advertDao.delete(adObject);
        }
    }

    public List<AdObject> searchTask(String search) {
        List<AdObject> taskList = new ArrayList<>();
        taskList = advertDao.queryBuilder()
                .whereOr(AdObjectDao.Properties.Content.like("%" + search + "%")
                        , AdObjectDao.Properties.Title.like("%" + search + "%")).list();
        return taskList;

    }

//    private ContentValues getContentValues(AdObject adObject) {
//        ContentValues values = new ContentValues();
//        values.put(AdvertDBSchema.Advert.Cols.UUID, adObject.getId().toString());
//        values.put(AdvertDBSchema.Advert.Cols.TITLE, adObject.getTitle());
//        values.put(AdvertDBSchema.Advert.Cols.DESCRIPTION, adObject.getContent());
//        values.put(AdvertDBSchema.Advert.Cols.PHONE, adObject.getPhone());
//        values.put(AdvertDBSchema.Advert.Cols.CITY, adObject.getCity());
//        values.put(AdvertDBSchema.Advert.Cols.VIP, adObject.isVip() ? 1 : 0);
//
//        return values;
//    }
    public Account getCurrentUser() {
    return currentUser;
}

    public static void setCurrentUser(Account user) {
        currentUser = user;
    }

    public void addUser(Account user) throws Exception{
        if (checkUsername(user)){
            throw new Exception("User Already Registered");
        }else{
            accountDao.insert(user);
        }
    }
    public boolean login(Account user){
        Long count = accountDao.queryBuilder().where(AccountDao.Properties.UserName.eq(user.getUserName()),AccountDao.Properties.Password.eq(user.getPassword())).count();
        if (count != 0) {
            setCurrentUser(accountDao.queryBuilder().where(AccountDao.Properties.UserName.eq(user.getUserName()),AccountDao.Properties.Password.eq(user.getPassword())).unique());
            return true;
        }else
            return false;
    }
    public boolean checkUsername(Account user){
        Long count = accountDao.queryBuilder().where(AccountDao.Properties.UserName.eq(user.getUserName())).count();
        return count != 0;
    }

    public File getPhotoFile(AdObject adObject) {
        return new File(mContext.getFilesDir(), adObject.getPhotoName());
    }

    private Repository(Context context) {

        mContext = context.getApplicationContext();
//        database = new AdvertOpenHelper(context).getWritableDatabase();
        SQLiteDatabase db = new AdOpenHelper(context).getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        advertDao = daoSession.getAdObjectDao();
        accountDao = daoSession.getAccountDao();
    }

}
