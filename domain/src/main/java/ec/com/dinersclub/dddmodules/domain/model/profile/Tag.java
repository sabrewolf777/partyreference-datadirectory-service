package ec.com.dinersclub.dddmodules.domain.model.profile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {
	 private String clave;
	 private String valor;
}
