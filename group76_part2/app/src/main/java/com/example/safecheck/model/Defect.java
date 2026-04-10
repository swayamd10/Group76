package com.example.safecheck.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "defects",
        foreignKeys = @ForeignKey(
                entity = SafetyCheck.class,
                parentColumns = "checkId",
                childColumns = "checkId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Defect {
    @PrimaryKey(autoGenerate = true)
    private int defectId;
    private int checkId;
    private String description;
    private String severity;

    public Defect(int checkId,String description, String severity){
        this.checkId=checkId;
        this.description=description;
        this.severity=severity;
    }

    public int getDefectId() {
        return defectId;
    }

    public void setDefectId(int defectId) {
        this.defectId = defectId;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
