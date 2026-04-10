package com.example.safecheck.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.safecheck.dao.DefectDao;
import com.example.safecheck.dao.SafetyCheckDao;
import com.example.safecheck.model.Defect;
import com.example.safecheck.model.SafetyCheck;

@Database(entities = {SafetyCheck.class, Defect.class},version = 1,exportSchema = false)
public abstract class SafeCheckDatabase extends RoomDatabase {
    private static volatile SafeCheckDatabase INSTANCE;

    public abstract SafetyCheckDao safetyCheckDao();
    public abstract DefectDao defectDao();

    public static SafeCheckDatabase getINSTANCE(Context context){
        if(INSTANCE==null){
            //here synchornised is used so that only one database is ever created even if multiple threads try to create it at the same time

            synchronized (SafeCheckDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),SafeCheckDatabase.class,"safecheck_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
