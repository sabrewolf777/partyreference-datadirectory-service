package ec.com.dinersclub.dddmodules.services;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;

public interface PRDataDirectoryCreateUseCase {
	public RecEvaluateCorporateProfileRs corporateProfileValidate(RecEvaluateCorporateProfileRq request,HttpHeaders headers);
}
