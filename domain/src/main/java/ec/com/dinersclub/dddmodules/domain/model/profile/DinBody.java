package ec.com.dinersclub.dddmodules.domain.model.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
public class DinBody {
    private String identificacionUsuario;
    private String perfil;
    private String usuarioBiometrico;
    private String transaccion;
    private String rucEmpresa;
    private String codigoOTP;
    private String token;
}