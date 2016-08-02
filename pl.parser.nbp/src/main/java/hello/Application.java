package hello;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private ExchangeRatesSeries rates = new ExchangeRatesSeries();

    public static void main(String args[]) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
      loadRates(args[0],args[1],args[2]);
        
    }
    
    public void loadRates(String code, String startDate, String endDate) {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    	
		RestTemplate restTemplate = new RestTemplate(); 
		Map<String,String> urlVariables = new HashMap<>();
		urlVariables.put("format", "xml");
		String url = "http://api.nbp.pl/api/exchangerates/rates/C/" + code + "/" + startDate + "/" + endDate + "/";
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, urlVariables);                   	   
	    
    	String string = response.getBody();    	
    	
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRatesSeries.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			 
			rates = (ExchangeRatesSeries) jaxbUnmarshaller.unmarshal((new StreamSource(new StringReader(string))));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	             
		double[] t = new double[rates.getRates().getRate().size()];
        
        for(int i=0;i<rates.getRates().getRate().size();i++){
        	t[i]=rates.getRates().getRate().get(i).getAsk();
        }        
        
        System.out.println(rates.getCode());
        DecimalFormat df = new DecimalFormat("0.0000");
        
        System.out.println(df.format(meanAritmetic(t)));
        System.out.println(df.format(standardDevation(t)));
		
            
    }
    
    public static double meanAritmetic(double[] t){
        double a = 0;
        for(double y : t){
            a += y;
        }       
 
        a /= t.length;
 
        return a;
    }
    
    public static double standardDevation(double[] t){
        double sD = 0;
        int l = t.length;
            for(double y : t){
                double r = (y - meanAritmetic(t));
                sD += (r*r);
        }
        sD /= l;
        sD = Math.sqrt(sD);
 
        return sD;
    }
}