package ec.com.dinersclub.dddmodules.infrastructure.outbound.mapper;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.dddmodules.domain.model.profile.DinBodyProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.DinProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.DinProfileRs;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs.PartyReference;
import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRs.StatusInstanceRecord;

public class MapperRECPRqToDinReq {

	private MapperRECPRqToDinReq() {}
	
	public static DinProfileRq mappeToDin(RecEvaluateCorporateProfileRq request, HttpHeaders headers) {
				
		DinBodyProfileRq body= new DinBodyProfileRq();
		if(request.getPartyReference().getIdentifications()[0].getPersonIdentificationType() == 3){
			body.setIdentificacion(request.getPartyReference().getIdentifications()[0].getIdentifier().getIdentifierValue());
		}else {
			body.setIdentificacionAdicional(request.getPartyReference().getIdentifications()[0].getIdentifier().getIdentifierValue());
		}
		
		return DinProfileRq.builder()
						   .dinBody(body)
						   .dinHeader(MapperDinHeader.mapperHeaderToDinnerHeader(headers))
						   .build();
							
	}
	
	
	
	public static RecEvaluateCorporateProfileRs mapperToRec(DinProfileRs response) {

		return RecEvaluateCorporateProfileRs.builder()
					.partyReference(PartyReference.builder()
											.corporateCustomer(response.getDinBody().isEsPersonaNaturalConRUC())
									.build())
					.statusInstanceRecord(StatusInstanceRecord.builder()
														      .statusType(response.getDinError().getTipo())
														      .transactionDate(response.getDinError().getFecha())
														      .status(response.getDinError().getOrigen())
														      .statusCode(response.getDinError().getCodigo())
														      .providerCode(response.getDinError().getCodigoErrorProveedor())
														      .message(response.getDinError().getMensaje())
														      .description(response.getDinError().getDetalle())
									.build())
				.build();
	}
	
}
