package com.example.safecheck.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.safecheck.dao.DefectDao;
import com.example.safecheck.dao.SafetyCheckDao;
import com.example.safecheck.database.SafeCheckDatabase;
import com.example.safecheck.model.Defect;
import com.example.safecheck.model.SafetyCheck;
import com.example.safecheck.model.SafetyCheckWithDefects;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SafetyRepo {
    private SafetyCheckDao safetyCheckDao;
    private DefectDao defectDao;
    private LiveData<List<SafetyCheck>> allSafetyCheck;

    private ExecutorService executorService; //this runs database and writes on the background thread

    public SafetyRepo(Context context){
        SafeCheckDatabase db = SafeCheckDatabase.getINSTANCE(context);
        safetyCheckDao=db.safetyCheckDao();
        defectDao=db.defectDao();
        allSafetyCheck=safetyCheckDao.getAllSafetyChecks();
        executorService= Executors.newSingleThreadExecutor();
    }

    //insert a safetycheck first then insert all its Defects here
    public void insertSafetyCheckWithDefects(SafetyCheck safetyCheck, List<Defect>defects){
        executorService.execute(()->{
            long newId= safetyCheckDao.insertSafetyCheck(safetyCheck);
            for(Defect defect : defects){
                defect.setCheckId((int) newId);
                defectDao.insertDefect(defect);
            }
        });
    }

    //Deleting a safetycheck(and then cascade will deletes its defetecs automatically)
    public  void deleteSafetyCheck(SafetyCheck safetyCheck){
        executorService.execute(()-> safetyCheckDao.deleteSafetyCheck(safetyCheck));

    }

    //here we are accessing all checks and then returning LiveData so that UI can update automatically
    public LiveData<List<SafetyCheck>> getAllSafetyCheck(){
        return allSafetyCheck;
    }

    //here we will get one check with all its defects which is called from background thread in ViewModel - for timebeing doesnt do anything
    public SafetyCheckWithDefects getSafetyCheckWithDefects(int id){
        return safetyCheckDao.getSafetyCheckWithDefects(id);
    }
}
