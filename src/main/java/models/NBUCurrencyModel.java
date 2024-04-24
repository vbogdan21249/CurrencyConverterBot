package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NBUCurrencyModel {
    @SerializedName("r030")
    private Integer curCode;

    @SerializedName("txt")
    private String curName;

    @SerializedName("rate")
    private BigDecimal curRate;

    @SerializedName("cc")
    private String curAbbreviation;

    @SerializedName("exchangedate")
    private String exchangeDate;
}
