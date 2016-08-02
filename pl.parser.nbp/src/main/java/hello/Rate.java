package hello;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rate")
@XmlAccessorType (XmlAccessType.FIELD)
public class Rate {
	
	private String No;
	
	private String EffectiveDate;
	
	private Double Bid;
	
	private Double Ask;

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getEffectiveDate() {
		return EffectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}

	public Double getBid() {
		return Bid;
	}

	public void setBid(Double bid) {
		Bid = bid;
	}

	public Double getAsk() {
		return Ask;
	}

	public void setAsk(Double ask) {
		Ask = ask;
	}

	
	
	

}
