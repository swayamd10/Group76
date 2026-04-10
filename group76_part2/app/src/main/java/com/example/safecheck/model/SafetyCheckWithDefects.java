package com.example.safecheck.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SafetyCheckWithDefects {
    @Embedded
    public SafetyCheck safety_check;

    @Relation(
            parentColumn="checkId",
            entityColumn = "checkId"
    )

    public List<Defect> defects;
}
