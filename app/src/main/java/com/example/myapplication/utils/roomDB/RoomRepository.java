package com.example.myapplication.utils.roomDB;

import com.example.myapplication.dto.EquationTableDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RoomRepository {
    private DbHelper dbHelper;
    public RoomRepository() {
    }
    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Completable setDbData(EquationTableDto data){
        return dbHelper.getEquationsTableDao().insertData(data);
    }

    public Single<List<EquationTableDto>> getAllDbData(){
        return dbHelper.getEquationsTableDao().getAllData();
    }

}
