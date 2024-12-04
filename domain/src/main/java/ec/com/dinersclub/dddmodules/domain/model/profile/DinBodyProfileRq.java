package ec.com.dinersclub.dddmodules.domain.model.profile;

import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq.PartyReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DinBodyProfileRq {
	private String identificacion;
	private String identificacionAdicional;
}
