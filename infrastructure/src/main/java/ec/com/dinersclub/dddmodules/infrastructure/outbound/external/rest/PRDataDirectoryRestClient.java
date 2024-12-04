package ec.com.dinersclub.dddmodules.infrastructure.outbound.external.rest;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;

public interface PRDataDirectoryRestClient {
	public RecEvaluateCorporateProfileRs corporateProfileValidate(RecEvaluateCorporateProfileRq request, HttpHeaders headers);
}
