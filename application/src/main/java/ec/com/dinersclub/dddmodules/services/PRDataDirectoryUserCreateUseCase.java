package ec.com.dinersclub.dddmodules.services;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs;

public interface PRDataDirectoryUserCreateUseCase {
	RecRegisterPartyReferenceDataDirectoryEntryRs createUser(RecRegisterPartyReferenceDataDirectoryEntryRq request,HttpHeaders headers);
}
