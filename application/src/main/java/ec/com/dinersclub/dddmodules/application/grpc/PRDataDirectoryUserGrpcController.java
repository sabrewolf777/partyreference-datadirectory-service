package ec.com.dinersclub.dddmodules.application.grpc;

import ec.com.dinersclub.dddmodules.application.grpc.CreateUserServiceGrpc.CreateUserServiceImplBase;
import ec.com.dinersclub.dddmodules.application.grpc.RecRegisterPartyReferenceDataDirectoryEntryRequest.PartyReference;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.Association;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.BiometricInstanceRecord;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.ContactPoint;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.Identification;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.Identifier;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.IdentifierValue;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.Organisation;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.OrganisationIdentification;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.PartyLegalEntityReference;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.PartyProfile;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.PartyReferenceDataDirectoryEntry;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.PasswordInstanceRecord;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.PaymentCard;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.ProductInstanceRecord;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.TokenAssignment;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq.TokenIdentificationCode;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs;
import ec.com.dinersclub.dddmodules.services.PRDataDirectoryUserCreateUseCase;
import io.grpc.Metadata;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

@GrpcService
public class PRDataDirectoryUserGrpcController extends CreateUserServiceImplBase {
	
	private static final Logger log = LoggerFactory.getLogger(PRDataDirectoryUserGrpcController.class.getName());
	
    @Autowired
    private PRDataDirectoryUserCreateUseCase prDataDirectoryUserCreateUseCase;
    
    private Metadata requestHeaders;
    
    @Override
    public void createUser(RecRegisterPartyReferenceDataDirectoryEntryRequest request, StreamObserver<RecRegisterPartyReferenceDataDirectoryEntryResponse> responseObserver) {
       
    	log.info("Request UserGrpc: {}",request);
		
		requestHeaders = (Metadata) StoreServer.HEADERS.get();
     	
    	HttpHeaders headers =getHeaders();
    	
    	
    	RecRegisterPartyReferenceDataDirectoryEntryRs response = prDataDirectoryUserCreateUseCase.createUser(toRec(request),headers);
        
        RecRegisterPartyReferenceDataDirectoryEntryResponse rs=null;
        
        log.info("Response UserGrpc: {}",rs);
        
        responseObserver.onNext(rs);
        responseObserver.onCompleted();
    }
    
    
    
    
    private RecRegisterPartyReferenceDataDirectoryEntryRq toRec(RecRegisterPartyReferenceDataDirectoryEntryRequest request) {
    	
    	Identification [] identification= new Identification[request.getPartyReferenceDataDirectoryEntry().getPartyReference().getIdentificationsCount()];
    	Association[] associations= new Association[request.getAssociationsCount()];
    	ContactPoint [] contactPoint= new ContactPoint[request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPointCount()];
    	OrganisationIdentification[] organisationIdentification =new OrganisationIdentification[request.getPartyLegalEntityReference().getOrganisation().getOrganisationIdentificationCount()]; 

    	for(int x=0; x < request.getAssociationsCount(); x++) {
    		associations[x]= Association.builder()
    									.productInstanceRecord(ProductInstanceRecord.builder()
    																				.paymentCard(PaymentCard.builder()
    																										.cardNumber(request.getAssociations(x).getProductInstanceRecord().getPaymentCard().getCardNumber())
    																										.cardExpiryDate(request.getAssociations(x).getProductInstanceRecord().getPaymentCard().getCardExpiryDate())
    																										.cardSecurityCode(request.getAssociations(x).getProductInstanceRecord().getPaymentCard().getCardSecurityCode())
    																							.build())
    															.build())
    						.build();    				
    	}
    	
    	for(int x=0; x < request.getPartyReferenceDataDirectoryEntry().getPartyReference().getIdentificationsCount(); x++) {
    		
    		identification[x]=Identification.builder()
					    					.identifier(Identifier.builder()
													  			  .identifierValue(request.getPartyReferenceDataDirectoryEntry().getPartyReference()
													  					  			      .getIdentifications(x).getIdentifier().getIdentifierValue())
													  	.build())
					    					.build();
    	}
    	
    	for(int x=0; x < request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPointCount(); x++) {
    		contactPoint[x]= ContactPoint.builder()
    									 .contactPointType(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPoint(x).getContactPointType())
    									 .contactPointValue(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPoint(x).getContactPointValue())
    						 .build();
    	}
    	
    	
    	for(int x=0; x < request.getPartyLegalEntityReference().getOrganisation().getOrganisationIdentificationCount();x++) {
    		organisationIdentification[x]=OrganisationIdentification.builder()
    																.identifier(Identifier.builder()
			    				                                              			  .identifierValue(request.getPartyLegalEntityReference().getOrganisation()
			    				                                            		  				       .getOrganisationIdentification(x).getIdentifier().getIdentifierValue())	
			    															    .build())
    																.build();	
    	}
    	
    	return RecRegisterPartyReferenceDataDirectoryEntryRq.builder()
    							.username(request.getUsername())
    							.passwordInstanceRecord(PasswordInstanceRecord.builder()
    																		  .authenticationMobilePassword(request.getPasswordInstanceRecord().getAuthenticationMobilePassword())
    																		  .authenticationPasswordStoredValue(request.getPasswordInstanceRecord().getAuthenticationPasswordStoredValue())
    																		  .build())
    							.partyReferenceDataDirectoryEntry(PartyReferenceDataDirectoryEntry.builder()
    												.partyReference(ec.com.dinersclub.dddmodules.domain.model.user.
    															    RecRegisterPartyReferenceDataDirectoryEntryRq.PartyReference.builder()
    															    .identifications(identification)
    															    .partyProfile(PartyProfile.builder()
    															    			.investmentNumber(request.getPartyReferenceDataDirectoryEntry()
    															    							  .getPartyReference().getPartyProfile().getInvestmentNumber())	
    															    			.build())
    															    .partyType(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getPartyType())
    															    .contactPoint(contactPoint)
    															    .corporateCustomer(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getCorporateCustomer())
    												.build())
    									.build())
    							.temporaryUserName(request.getTemporaryUserName())
    							.associations(associations)
    							.tokenAssignment(TokenAssignment.builder()
    										 .previousToken(request.getTokenAssignment().getPreviousToken())
    										 .tokenIdentificationCode(TokenIdentificationCode.builder()
    												 					.identifierValue(IdentifierValue.builder()
    												 									 .value(request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue().getValue())
    												 									.build())
    												 					.build()) 
    										 .build())
    							.biometricInstanceRecord(BiometricInstanceRecord.builder()
    														.authenticationBiometricRecord(request.getBiometricInstanceRecord().getAuthenticationBiometricRecord())	
    														.build())
    							.partyLegalEntityReference(PartyLegalEntityReference.builder()
    																.organisation(Organisation.builder()
    																						  .organisationIdentification(organisationIdentification)
    																			  .build())
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