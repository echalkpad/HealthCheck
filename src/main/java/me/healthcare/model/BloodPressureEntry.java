package me.healthcare.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

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
    
    //@JsonProperty("MDate")
    //public long mDate;
    public Date mDate;
    @JsonSetter("MDate")
    private void setMDate(final long unixTime) {
    	mDate = new Date((long)unixTime*1000);
    }
    
    @JsonProperty("LastChangeTime")
    public long lastChangeTime;
    @JsonProperty("Note")
    public String note;
    @JsonProperty("userid")
    public String userId;
}
