package me.healthcare.model;

import java.util.List;

/**
 * Created by alex on 5/25/14.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BloodPressureData {

    @JsonProperty("BPDataList")
    public List<BloodPressureEntry> bpEntries;

    @JsonProperty("NextPageUrl")
    public String nextPageUrl;

    @JsonProperty("CurrentRecordCount")
    public int currentRecordCount;

    @JsonProperty("PageLength")
    public int pageLength;

    @JsonProperty("PageNumber")
    public int pageNumber;

    @JsonProperty("PrevPageUrl")
    public String prevPageUrl;

    @JsonProperty("RecordCount")
    public int recordCount;

    @JsonProperty("BPUnit")
    public int bpUnit;
}
