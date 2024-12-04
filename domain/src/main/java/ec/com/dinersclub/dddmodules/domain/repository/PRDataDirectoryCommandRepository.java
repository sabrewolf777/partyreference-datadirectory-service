package ec.com.dinersclub.dddmodules.domain.repository;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;

public interface PRDataDirectoryCommandRepository {
	public RecEvaluateCorporateProfileRs corporateProfileValidate(RecEvaluateCorporateProfileRq request,HttpHeaders headers);
}
