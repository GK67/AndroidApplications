package com.example.gk.dictionary;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//http://techin-android.blogspot.com/2011/11/copy-database-from-assets-to-local.html

//read
public class MedicineBaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "TopDrugs.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    //Constructor
    public MedicineBaseHelper(Context context) {

        super(context, DB_NAME, null, 2);
        this.myContext = context;
    }

    public void createDataBase() throws IOException
    {
        //If database not exists copy it from the assets
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            try
            {
                //Copy the database from assets
                copyDataBase();

                System.out.println("createDatabase database created");
            }
            catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
//https://stackoverflow.com/questions/22627215/how-to-put-database-and-read-database-from-assets-folder-android-which-are-creat
    private boolean checkDataBase()
    {
        File dbFile = new File(Environment.getDataDirectory() + "/data/" + myContext.getPackageName() + "/databases/" + DB_NAME);
        Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    //https://stackoverflow.com/questions/22301933/copy-database-from-assets-to-device-sd-card-removes-data-in-the-tables
    private void copyDataBase() throws IOException
    {

        InputStream mInput = myContext.getAssets().open(DB_NAME);
        Log.v("dbFile", "get Asset");

        String outFileName = Environment.getDataDirectory() + "/data/" + myContext.getPackageName() + "/databases";
        File file = new File(outFileName);
        file.mkdirs();
        Log.v("dbFile", outFileName);

        Log.v("dbFile", "get assets database");

        outFileName = Environment.getDataDirectory() + "/data/" + myContext.getPackageName() + "/databases/" + DB_NAME;

        OutputStream mOutput = new FileOutputStream(outFileName);
        Log.v("dbFile", "get output");
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        Log.v("dbFile", "done copying");
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public SQLiteDatabase openDataBase() throws SQLException {

        //Open the database
        String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return myDataBase;
    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}