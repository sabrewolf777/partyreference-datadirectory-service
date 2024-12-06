package ec.com.dinersclub.dddmodules.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ec.com.dinersclub.dddmodules.domain.model.user.DinnerCreateUserRq;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs;
import ec.com.dinersclub.dddmodules.domain.repository.PRDataDirectoryUserCommandRepository;
import ec.com.dinersclub.dddmodules.infrastructure.outbound.mapper.MapperCreateUsetRqToDinReq;

@Service
public class PRDataDirectoryUserCreateUseCaseImpl<V> implements PRDataDirectoryUserCreateUseCase{
		
	@Autowired
	PRDataDirectoryUserCommandRepository pRDataDirectoryUserCommandRepository;
	
	@Value("${msd.seg.cli.usuario.creacion}")
    String apiUrl;
	
	@Override
	public RecRegisterPartyReferenceDataDirectoryEntryRs createUser(RecRegisterPartyReferenceDataDirectoryEntryRq request, HttpHeaders headers) {
		
		final DinnerCreateUserRq rq= MapperCreateUsetRqToDinReq.toDinReq(request, headers) ;
		
		final Object resp= pRDataDirectoryUserCommandRepository.createUser(apiUrl,rq);
		
		return resp!= null ? MapperCreateUsetRqToDinReq.toRecRs(resp) : null;
	}

}
