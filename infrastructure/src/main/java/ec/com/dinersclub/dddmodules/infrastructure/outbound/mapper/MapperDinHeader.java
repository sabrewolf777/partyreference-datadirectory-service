package ec.com.dinersclub.dddmodules.infrastructure.outbound.mapper;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import ec.com.dinersclub.dddmodules.domain.model.profile.DinHeader;
import ec.com.dinersclub.dddmodules.domain.model.profile.Paginado;
import ec.com.dinersclub.dddmodules.domain.model.profile.Tag;

public class MapperDinHeader {
	
	private MapperDinHeader() {}

    private static final Logger log = LoggerFactory.getLogger(MapperDinHeader.class.getName());

	public static DinHeader mapperHeaderToDinnerHeader(final HttpHeaders headers) {
		
		final List<Tag> tags = new ArrayList<>();
       	tags.add(Tag.builder().clave(getHeaderValueAsString(headers,"tagsKeyValue"))
				        	  .valor(getHeaderValueAsString(headers,"tagsKeyValue"))
				        	  .build());
		
		return DinHeader.builder()
				.aplicacionId(getHeaderValueAsString(headers,"applicationId")) 
				.canalId( getHeaderValueAsString(headers,"channelId"))
				.sesionId(getHeaderValueAsString(headers,"sesionId"))
				.dispositivo(getHeaderValueAsString(headers,"device"))
				.idioma(getHeaderValueAsString(headers,"content-language"))
				.portalId(getHeaderValueAsString(headers,"portalId"))
				.uuid(getHeaderValueAsString(headers,"uuid"))
				.ip(getHeaderValueAsString(headers,"ipaddress"))
				.horaTransaccion(getHeaderValueAsString(headers,"transactionDate"))
				.llaveSimetrica(getHeaderValueAsString(headers,"simetricKey"))
				.usuario(getHeaderValueAsString(headers,"userId"))
				.paginado(Paginado.builder()
							  .cantRegistros(getHeaderValueAsInt(headers,"recordsAmount"))
							  .numPagActual(getHeaderValueAsInt(headers,"pagesCurrentIndex"))
							  .numTotalPag(getHeaderValueAsInt(headers,"pagesAmount"))
						  .build())
				.tags(tags)
		.build();
	}
	
	public static int getHeaderValueAsInt(HttpHeaders headers, String headerName) {
        String headerValue = headers.getFirst(headerName);
        if (headerValue != null) {
            try {
                return Integer.parseInt(headerValue);
            } catch (NumberFormatException e) {
            	log.error("El valor del encabezado no es un número válido: {}", headerValue);
            }
        }
        return 0;
     }
    
    public static String getHeaderValueAsString(HttpHeaders headers, String headerName) {
        final String headerValue = headers.getFirst(headerName);
        if(headerValue != null) {
        	 return headerValue;
        }
        return "";
    }
	
	
}
