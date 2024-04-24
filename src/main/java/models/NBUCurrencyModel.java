package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class NBUCurrencyModel {
    @SerializedName("r030")
    private Integer curCode;

    @SerializedName("txt")
    private String curName;

    @SerializedName("rate")
    private Double curRate;

    @SerializedName("cc")
    private String curAbbreviation;

    @SerializedName("exchangedate")
    private String exchangeDate;
}
