package exchangerate;


import java.math.BigDecimal;
import java.util.List;

public class CurrencyConverter {
    private List<ExchangeRate> exchangeRates ;

    public CurrencyConverter(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public BigDecimal convert(String from, String to, BigDecimal amount) {
        ExchangeRate rate = null;
        if(!exchangeRates.isEmpty()){
            rate  =  exchangeRates.stream()
                    .filter(exchangeRate ->exchangeRate.getTo().equalsIgnoreCase(to)&&exchangeRate.getFrom().equalsIgnoreCase(from))
                    .findAny()
                    .orElse(null);
        }

        if(rate!=null && amount.compareTo(BigDecimal.ZERO)>0){
            return amount.multiply(rate.getRate());
        }else{
            return BigDecimal.ZERO;
        }
    }
}
