package ec.com.dinersclub.dddmodules.infrastructure.outbound.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.dddmodules.domain.model.user.DinnerCreateUserRq;
import ec.com.dinersclub.dddmodules.domain.model.user.DinnerCreateUserRq.DinBody;
import ec.com.dinersclub.dddmodules.domain.model.user.DinnerCreateUserRs;
import ec.com.dinersclub.dddmodules.domain.model.user.DinnerCreateUserRs.DinError;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs.DirectoryEntryDate;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs.DocumentInstanceRecord;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs.PartyReference;
import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRs.StatusInstanceRecord;

public class MapperCreateUsetRqToDinReq {

	private static final Logger log = LoggerFactory.getLogger(MapperCreateUsetRqToDinReq.class);
	
	private MapperCreateUsetRqToDinReq() {}
	
	
	public static DinnerCreateUserRq toDinReq(RecRegisterPartyReferenceDataDirectoryEntryRq request,HttpHeaders headers) {
		
		DinBody dinBody= new DinBody();
		
		for(int x=0; x < request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPoint().length;x++) {
			if(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPoint()[x].getContactPointType().equals("Cellphone")) {
				dinBody.setCelular(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPoint()[x].getContactPointValue());
			}else {
				dinBody.setCorreo(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getContactPoint()[x].getContactPointValue());
			}
		}
		
		dinBody.setNombreUsuario(request.getUsername());
		dinBody.setClave(request.getPasswordInstanceRecord()!=null ? request.getPasswordInstanceRecord().getAuthenticationPasswordStoredValue() : "");
		dinBody.setClaveMobile(request.getPasswordInstanceRecord()!=null ? request.getPasswordInstanceRecord().getAuthenticationMobilePassword() : "");
		
		for(int x=0; x < request.getPartyReferenceDataDirectoryEntry().getPartyReference().getIdentifications().length;x++) {
			dinBody.setNroIdentificacion(Long.parseLong(request.getPartyReferenceDataDirectoryEntry().getPartyReference()
															   .getIdentifications()[x].getIdentifier().getIdentifierValue()));
		}
		
		
		dinBody.setNumeroDeInversion(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getPartyProfile().getInvestmentNumber());
		dinBody.setPerfil(request.getPartyReferenceDataDirectoryEntry().getPartyReference().getPartyType());
		dinBody.setEsPersonaNaturalConRUC(request.getPartyReferenceDataDirectoryEntry().getPartyReference().isCorporateCustomer());
		dinBody.setUsuarioTemporal(request.getTemporaryUserName());
		
		for(int x=0; x < request.getAssociations().length; x++) {
			dinBody.setDigitosTarjeta(request.getAssociations()[x].getProductInstanceRecord().getPaymentCard().getCardNumber());
			dinBody.setFechaCaducidad(request.getAssociations()[x].getProductInstanceRecord().getPaymentCard().getCardExpiryDate());
			dinBody.setCodigoVerificacion(request.getAssociations()[x].getProductInstanceRecord().getPaymentCard().getCardSecurityCode());			
		}
		
		
		dinBody.setTipoDigitos(request.getAuthenticationMethod());
		dinBody.setCreacionPrevia(request.getTokenAssignment().isPreviousToken());
		dinBody.setToken(request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue().getValue());
		dinBody.setCodigoDactilar(request.getBiometricInstanceRecord().getAuthenticationBiometricRecord());
		
		for(int x=0; x < request.getPartyLegalEntityReference().getOrganisation().getOrganisationIdentification().length; x++) {
			dinBody.setRucEmpresa(request.getPartyLegalEntityReference().getOrganisation().getOrganisationIdentification()[x].getIdentifier().getIdentifierValue());
		}
		
		dinBody.setOrigenInvocacion(request.getSourceRequest());
		
		
		return DinnerCreateUserRq.builder()
								 .dinHeader(MapperDinHeader.mapperHeaderToDinnerHeader(headers))
								 .dinBody(dinBody)
			   .build();	
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static <V> RecRegisterPartyReferenceDataDirectoryEntryRs toRecRs(Object resp) {
		
		final LinkedHashMap<String, Map<String,String>> map=(LinkedHashMap<String, Map<String,String>>) resp;
		final DinnerCreateUserRs request= new DinnerCreateUserRs();
		map.forEach((k,v) ->{
			log.info("key: {}, value: {}",k,v);
			if(k.equals("dinBody")) {
				request.setDinBody(ec.com.dinersclub.dddmodules.domain.model.user.DinnerCreateUserRs.DinBody.builder()
									.repuestaCreacionUsuario(v.get("repuestaCreacionUsuario"))
									.numeroIdentificacionUnico(v.get("numeroIdentificacionUnico"))
									.fechaTransaccion(v.get("fechaTransaccion"))
									.horaTransaccion(v.get("horaTransaccion"))
									.imagenAvatar(v.get("imagenAvatar"))
									.codigoAplicacionUnico(v.get("codigoAplicacionUnico"))
									.build());
			}else if(k.equals("dinError")) {
				request.setDinError(DinError.builder().tipo(v.get("tipo")).codigo(v.get("codigo"))
													  .codigoErrorProveedor(v.get("codigoErrorProveedor"))
													  .detalle(v.get("detalle")).fecha(v.get("fecha"))
													  .mensaje(v.get("mensaje")).origen(v.get("origen"))
									.build());
			}
		});
				
		return RecRegisterPartyReferenceDataDirectoryEntryRs.builder()
							.partyReferenceDataDirectoryEntry(ec.com.dinersclub.dddmodules.domain.model.user.
															  RecRegisterPartyReferenceDataDirectoryEntryRs.PartyReferenceDataDirectoryEntry.builder()
															  .directoryEntryStatus(request.getDinBody().getRepuestaCreacionUsuario())
															  .partyReference(PartyReference.builder()
																	  			.referenceId(request.getDinBody().getNumeroIdentificacionUnico())
																	  			.build())
															  .directoryEntryDate(DirectoryEntryDate.builder()
																	  				.directoryEntryValueDate(request.getDinBody().getFechaTransaccion())
																	  				.directoryEntryValueTime(request.getDinBody().getHoraTransaccion())
																	  				.build())
															  
															  .build())
							.documentInstanceRecord(DocumentInstanceRecord.builder()
															.documentContent(request.getDinBody().getImagenAvatar())
													.build())
							.applicationCode(request.getDinBody().getCodigoAplicacionUnico())
							.statusInstanceRecord(StatusInstanceRecord.builder()
																	.statusType(request.getDinError().getTipo())
																	.transactionDate(request.getDinError().getFecha())
																	.status(request.getDinError().getOrigen())
																	.statusCode(request.getDinError().getCodigo())
																	.providerCode(request.getDinError().getCodigoErrorProveedor())
																	.message(request.getDinError().getMensaje())
																	.description(request.getDinError().getDetalle())
												  .build())
							.build();
	}
	
}
