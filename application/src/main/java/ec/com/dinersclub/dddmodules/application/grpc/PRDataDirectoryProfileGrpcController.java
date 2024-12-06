package ec.com.dinersclub.dddmodules.application.grpc;

import ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileResponse.StatusInstanceRecord;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq.Identification;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq.Identifier;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq.PartyReference;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;
import ec.com.dinersclub.dddmodules.services.PRDataDirectoryCreateUseCase;
import io.grpc.Metadata;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

@GrpcService
public class PRDataDirectoryProfileGrpcController extends ec.com.dinersclub.dddmodules.application.grpc.CorporateProfileServiceGrpc.CorporateProfileServiceImplBase {
	
	private static final Logger log = LoggerFactory.getLogger(PRDataDirectoryProfileGrpcController.class.getName());
	
    @Autowired
    private PRDataDirectoryCreateUseCase pRDataDirectoryCreateUseCase;
    
    private Metadata requestHeaders;

    @Override
    public void corporateProfileValidate(ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileRequest request, 
    					StreamObserver<ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileResponse> responseObserver) {
    	try {	 
    		
    		log.info("Request Grpc: {}",request);
    		
    		requestHeaders = (Metadata) StoreServer.HEADERS.get();
	     	
	    	HttpHeaders headers =getHeaders();
	    	
	    	RecEvaluateCorporateProfileRs response = pRDataDirectoryCreateUseCase.corporateProfileValidate(grpcRqtoRecRq(request),headers);
	        
	    	ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileResponse rs = recRqToGrpcRq(response);
	        
	    	log.info("Response Grpc: {}",rs);
	    	
	        responseObserver.onNext(rs);
	        
	        responseObserver.onCompleted();
	        
    	} catch (Exception e) {
    		log.error("ERROR en corporateProfileValidate: {}",e.getMessage());
            responseObserver.onError(e);
        }
    }
        
    
    private RecEvaluateCorporateProfileRq grpcRqtoRecRq(ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileRequest request) {
    	
    	final Identification [] identification = new Identification[request.getPartyReference().getIdentificationsCount()];
    	
    	for(int x=0; x < request.getPartyReference().getIdentificationsCount(); x++) {
    		ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileRequest.
    		PartyReference.Identification ide= request.getPartyReference().getIdentifications(x);
    		
    		identification[x]= Identification.builder()
    										 .identifier(Identifier.builder().identifierValue(ide.getIdentifier().getIdentifierValue()).build())
    							.build();
    		identification[x].setPersonIdentificationType(ide.getPersonIdentificationType());
    	}
    	
    	return RecEvaluateCorporateProfileRq.builder().partyReference(PartyReference.builder()
							    					  .identifications(identification)	
							    					  .build())
							    			.build();
    }
    
    
    private ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileResponse recRqToGrpcRq(RecEvaluateCorporateProfileRs request){
		
    	
    	return RecEvaluateCorporateProfileResponse.newBuilder()
    			.setPartyReference(ec.com.dinersclub.dddmodules.application.grpc.RecEvaluateCorporateProfileResponse.PartyReference.newBuilder()
    					.setCorporateCustomer(request.getPartyReference().isCorporateCustomer())
    					.build())
    			.setStatusInstanceRecord(StatusInstanceRecord.newBuilder()
    					.setDescription(request.getStatusInstanceRecord().getDescription())
    					.setProviderCode(request.getStatusInstanceRecord().getProviderCode())
    					.setStatus(request.getStatusInstanceRecord().getStatus())
    					.setStatusCode(request.getStatusInstanceRecord().getStatusCode())
    					.setTransactionDate(request.getStatusInstanceRecord().getTransactionDate())
    					.setMessage(request.getStatusInstanceRecord().getMessage())
    					.setStatusType(request.getStatusInstanceRecord().getStatusType())
    					.build())
    			.build();	
    }
    
    
    
    private HttpHeaders getHeaders() {	
    	HttpHeaders headers= new HttpHeaders();
    	headers.add("applicationId", getHeader("applicationId"));
    	headers.add("sesionId", getHeader("sesionId"));
    	headers.add("channelId", getHeader("channelId"));
    	headers.add("device", getHeader("device"));
    	headers.add("portalId", getHeader("portalId"));
    	headers.add("uuid", getHeader("uuid"));
    	headers.add("ipaddress", getHeader("ipaddress"));
    	headers.add("transactionDate", getHeader("transactionDate"));
    	headers.add("simetricKey", getHeader("simetricKey"));
    	headers.add("userId", getHeader("userId"));
    	headers.add("recordsAmount", getHeader("recordsAmount"));
    	headers.add("pagesAmount", getHeader("pagesAmount"));
    	headers.add("pagesCurrentIndex", getHeader("pagesCurrentIndex"));
    	headers.add("pagsKeyValue", getHeader("pagsKeyValue"));   	
    	return headers;
    }
    
    private String getHeader(String key) {
        Metadata.Key<String> metadataKey = Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER);
        return requestHeaders.get(metadataKey);
    }
    
    public String getValueAsString(String value) {
        final String valueReturn = value;
        if(valueReturn != null) {
        	 return valueReturn;
        }
        return "";
    }
}
