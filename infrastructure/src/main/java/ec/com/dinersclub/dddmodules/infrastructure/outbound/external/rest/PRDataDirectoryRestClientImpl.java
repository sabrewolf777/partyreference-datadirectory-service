package ec.com.dinersclub.dddmodules.infrastructure.outbound.external.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ec.com.dinersclub.dddmodules.domain.model.profile.DinProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.DinProfileRs;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;
import ec.com.dinersclub.dddmodules.infrastructure.outbound.mapper.MapperRECPRqToDinReq;

@Service
public class PRDataDirectoryRestClientImpl implements PRDataDirectoryRestClient{

    private static final Logger log = LoggerFactory.getLogger(PRDataDirectoryRestClientImpl.class.getName());
    
	@Value("${msd.seg.verify.person.api.url}")
	String apiUrl;
	
	public RecEvaluateCorporateProfileRs corporateProfileValidate(RecEvaluateCorporateProfileRq request, HttpHeaders headers) {
		
		final RestTemplate restTemplate= new RestTemplate();
		
		final HttpHeaders hdrs = new HttpHeaders();
		
		final DinProfileRq reqDinners= MapperRECPRqToDinReq.mappeToDin(request,headers);
		
		log.info(" Request Dinners: apiUrl: {},params: {}",apiUrl, reqDinners);
		
		final HttpEntity<DinProfileRq> entity = new HttpEntity<>(reqDinners, hdrs);

	    final ResponseEntity<DinProfileRs> respDinners = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, DinProfileRs.class);
		
		log.info(" Response Dinners: {}",respDinners);	
		
		return MapperRECPRqToDinReq.mapperToRec(respDinners.getBody());
	}
	
		    
}
