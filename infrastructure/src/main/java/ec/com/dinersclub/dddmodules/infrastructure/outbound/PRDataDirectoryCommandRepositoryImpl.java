package ec.com.dinersclub.dddmodules.infrastructure.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;
import ec.com.dinersclub.dddmodules.domain.repository.PRDataDirectoryCommandRepository;
import ec.com.dinersclub.dddmodules.infrastructure.outbound.external.rest.PRDataDirectoryRestClient;

@Repository
public class PRDataDirectoryCommandRepositoryImpl implements PRDataDirectoryCommandRepository {

	@Autowired
	PRDataDirectoryRestClient pRDataDirectoryRestClient;
	
	@Override
	public RecEvaluateCorporateProfileRs corporateProfileValidate(RecEvaluateCorporateProfileRq request, HttpHeaders headers) {
		return pRDataDirectoryRestClient.corporateProfileValidate(request, headers);
	}

}
