package ec.com.dinersclub.dddmodules.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;
import ec.com.dinersclub.dddmodules.domain.repository.PRDataDirectoryCommandRepository;

@Service
public class PRDataDirectoryCreateUseCaseImpl implements PRDataDirectoryCreateUseCase {

	@Autowired
	PRDataDirectoryCommandRepository pRDataDirectoryCommandRepository;
	
	public RecEvaluateCorporateProfileRs corporateProfileValidate(RecEvaluateCorporateProfileRq request,HttpHeaders headers) {
		return pRDataDirectoryCommandRepository.corporateProfileValidate(request, headers);
	}
}
