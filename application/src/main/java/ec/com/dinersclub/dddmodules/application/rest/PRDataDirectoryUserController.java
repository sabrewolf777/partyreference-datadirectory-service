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
import ec.com.dinersclub.dddmodules.application.validate.ValidPRDataDirectoryUserRequest;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs;
import ec.com.dinersclub.dddmodules.services.PRDataDirectoryUserCreateUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/v1/partyReferenceDataDirectoryEntry")
public class PRDataDirectoryUserController {

	 private static final Logger log = LoggerFactory.getLogger(PRDataDirectoryUserController.class);
	
	 @Autowired
	 PRDataDirectoryUserCreateUseCase prDataDirectoryUserCreateUseCase;
	 
	 @PostMapping("/register")
	 public ResponseEntity<RecRegisterPartyReferenceDataDirectoryEntryRs> createUser(@Valid @ValidPRDataDirectoryUserRequest @RequestBody RecRegisterPartyReferenceDataDirectoryEntryRq request,
			 																		 @Valid @ValidPRDataDirectoryHeaders @RequestHeader HttpHeaders headers) {
	        log.info("corporateProfileValidate request REST: {}, headers:{}",request,headers);
	        return new ResponseEntity<>( prDataDirectoryUserCreateUseCase.createUser(request, headers),HttpStatus.OK);
	 }
}
