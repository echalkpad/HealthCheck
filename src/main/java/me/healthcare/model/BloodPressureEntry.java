package me.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alex on 5/25/14.
 */
public class BloodPressureEntry {

    @JsonProperty("BPL")
    public int bpl;
    @JsonProperty("HP")
    public double systolic;
    @JsonProperty("HR")
    public int hr;
    @JsonProperty("IsArr")
    public int isArr;
    @JsonProperty("LP")
    public double diastolic;
    @JsonProperty("Lat")
    public double lat;
    @JsonProperty("Lon")
    public double lon;
    @JsonProperty("DataID")
    public String dataId;
    @JsonProperty("MDate")
    public long mDate;
    @JsonProperty("LastChangeTime")
    public long lastChangeTime;
    @JsonProperty("Note")
    public String note;
    @JsonProperty("userid")
    public String userId;
}
