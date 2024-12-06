package ec.com.dinersclub.dddmodules.infrastructure.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.com.dinersclub.dddmodules.domain.repository.PRDataDirectoryUserCommandRepository;
import ec.com.dinersclub.dddmodules.infrastructure.outbound.external.rest.ExternalServicePort;

@Repository
public class PRDataDirectoryUserCommandRepositoryImpl implements PRDataDirectoryUserCommandRepository {

	@Autowired
	ExternalServicePort externalServicePort;
	
	public  <T> T  createUser(String url, T request) {
		return (T) externalServicePort.sendDataToExternalService(url, request);
	}

}
