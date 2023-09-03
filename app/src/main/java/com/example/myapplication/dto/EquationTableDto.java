package com.example.myapplication.dto;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "equations_table")
public class EquationTableDto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String equation;

    public EquationTableDto(String date, String equation) {
        this.date = date;
        this.equation = equation;
    }

    public String getDate() {
        return date;
    }

    public String getEquation() {
        return equation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
