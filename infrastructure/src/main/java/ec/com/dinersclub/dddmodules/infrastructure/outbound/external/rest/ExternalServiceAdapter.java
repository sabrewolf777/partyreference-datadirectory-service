package ec.com.dinersclub.dddmodules.infrastructure.outbound.external.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceAdapter implements ExternalServicePort{

     private static final Logger log = LoggerFactory.getLogger(ExternalServiceAdapter.class.getName());
	
	 @Override
	 @SuppressWarnings("unchecked")
	 public <T> T sendDataToExternalService(String url, T data) {
		 
		 log.info("ExternalServiceAdapter: url: {} , params: {}",url,data);
		 
    	 final RestTemplate restTemplate= new RestTemplate();
    	
		 final HttpHeaders hdrs = new HttpHeaders();
		 
		 final HttpEntity<T> entity = new HttpEntity<>(data, hdrs);
		 
		 
		 final ResponseEntity<Object> response =  restTemplate.exchange(url,HttpMethod.POST,entity,Object.class);
		 
		 log.info("response: ExternalServiceAdapter {}",response);
		 	 
	     return (T) response.getBody();
	 }

}
