package exchangerate;

import java.math.BigDecimal;

public class ExchangeRate {
    private String from;
    private String to ;
    private BigDecimal rate;

    public ExchangeRate(String from, String to, BigDecimal rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
