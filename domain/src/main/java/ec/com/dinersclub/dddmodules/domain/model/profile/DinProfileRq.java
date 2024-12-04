package ec.com.dinersclub.dddmodules.domain.model.profile;

import lombok.*;

@Data
@Builder
public class DinProfileRq {
    private DinHeader dinHeader;
    private DinBodyProfileRq dinBody;

} 