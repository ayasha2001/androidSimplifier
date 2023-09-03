package com.example.myapplication.utils.roomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.dto.EquationTableDto;

@Database(entities = {EquationTableDto.class}, version = 1, exportSchema = false)
public abstract class DbHelper extends RoomDatabase {

    public abstract EquationsTableDao getEquationsTableDao();
    private static DbHelper dbHelper;

    public static synchronized DbHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = Room.databaseBuilder(context.getApplicationContext(),
                            DbHelper.class, "Room_DB")
                    .build();
        }

        return dbHelper;
    }
}
