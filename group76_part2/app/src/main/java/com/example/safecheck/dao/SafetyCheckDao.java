package com.example.safecheck.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.safecheck.model.SafetyCheck;
import com.example.safecheck.model.SafetyCheckWithDefects;

import java.util.List;

@Dao
public interface SafetyCheckDao {
    @Insert
    long insertSafetyCheck(SafetyCheck safetyCheck);

    @Delete
    void deleteSafetyCheck(SafetyCheck safetyCheck);

//    here we are using livedata because this will automatically notify UI to update when the databases has any cahges.
    @Query("SELECT * FROM safetyChecks ORDER BY checkId DESC")
    LiveData<List<SafetyCheck>> getAllSafetyChecks();

    @Transaction
    @Query("SELECT * FROM safetyChecks WHERE checkId =:id")
    SafetyCheckWithDefects getSafetyCheckWithDefects(int id);
}
