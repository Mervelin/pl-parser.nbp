package hello;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
@XmlAccessorType (XmlAccessType.FIELD)
public class ExchangeRatesSeries {
	
	private String Table;
	
	private String Currency;
	
	private String Code;
	
	@XmlElement(name = "Rates")
	private Rates Rates;

	public String getTable() {
		return Table;
	}

	public void setTable(String table) {
		Table = table;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public Rates getRates() {
		return Rates;
	}

	public void setRates(Rates rates) {
		Rates = rates;
	}
	
	

}
