package ec.com.dinersclub.dddmodules.domain.model.user;

import java.util.List;

import ec.com.dinersclub.dddmodules.domain.model.profile.DinHeader;
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
public class DinnerCreateUserRq {
    private DinHeader dinHeader;
    private DinBody dinBody;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DinBody {
        private String nombreUsuario;
        private String clave;
        private long   nroIdentificacion;
        private String numeroDeInversion;
        private String digitosTarjeta;
        private String usuarioTemporal;
        private String perfil;
        private String celular;
        private boolean creacionPrevia;
        private String token;
        private boolean esPersonaNaturalConRUC;
        private String codigoDactilar;
        private String rucEmpresa;
        private String correo;
        private String claveMobile;
        private String fechaCaducidad;
        private String codigoVerificacion;
        private String tipoDigitos;
        private String origenInvocacion;
    }
}
