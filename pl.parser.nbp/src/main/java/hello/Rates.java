package hello;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rates")
@XmlAccessorType (XmlAccessType.FIELD)
public class Rates {
	
	@XmlElement(name = "Rate")
	private List<Rate> Rate;

	public List<Rate> getRate() {
		return Rate;
	}

	public void setRate(List<Rate> rate) {
		Rate = rate;
	}
	
	

}
