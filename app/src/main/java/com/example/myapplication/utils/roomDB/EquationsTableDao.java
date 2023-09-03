package com.example.myapplication.utils.roomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.dto.EquationTableDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface EquationsTableDao {

    @Query("select * from equations_table")
    Single<List<EquationTableDto>> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertData(EquationTableDto equationTableDto);

}
