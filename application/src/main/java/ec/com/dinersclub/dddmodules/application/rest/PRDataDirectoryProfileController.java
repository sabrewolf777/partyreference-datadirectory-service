package ec.com.dinersclub.dddmodules.application.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.com.dinersclub.dddmodules.application.validate.ValidPRDataDirectoryHeaders;
import ec.com.dinersclub.dddmodules.application.validate.ValidPRDataDirectoryProfileRequest;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;
import ec.com.dinersclub.dddmodules.services.PRDataDirectoryCreateUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/v1/corporateProfile")
public class PRDataDirectoryProfileController {

	private static final Logger log = LoggerFactory.getLogger(PRDataDirectoryProfileController.class.getName());

	@Autowired	
	PRDataDirectoryCreateUseCase pRDataDirectoryCreateUseCase;
	
	@PostMapping("/evaluate")
	public ResponseEntity<RecEvaluateCorporateProfileRs> corporateProfileValidate(@Valid @ValidPRDataDirectoryProfileRequest @RequestBody RecEvaluateCorporateProfileRq request,
	    														   				  @Valid @ValidPRDataDirectoryHeaders @RequestHeader HttpHeaders headers) {
	        log.info("corporateProfileValidate request REST: {}, headers:{}",request,headers);
	        return new ResponseEntity<>( pRDataDirectoryCreateUseCase.corporateProfileValidate(request, headers),HttpStatus.OK);
	}

}
