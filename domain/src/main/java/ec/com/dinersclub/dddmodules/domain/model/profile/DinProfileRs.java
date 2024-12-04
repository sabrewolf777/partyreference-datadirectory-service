package ec.com.dinersclub.dddmodules.domain.model.profile;

import ec.com.dinersclub.dddmodules.domain.model.profile.DinOtpRs.DinError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DinProfileRs {
		private DinBodyProfileRs dinBody;
		private DinError dinError;
}
