package ec.com.dinersclub.dddmodules.domain.model.user;

import java.util.List;
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
public class DinnerCreateUserRs {
    private DinHeader dinHeader;
    private DinBody   dinBody; // Puede ser null
    private DinError  dinError;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DinHeader {
        private String aplicacionId;
        private String canalId;
        private String sesionId;
        private String dispositivo;
        private String idioma;
        private String portalId;
        private String uuid;
        private String ip;
        private String horaTransaccion;
        private String llaveSimetrica;
        private String usuario;
        private Paginado paginado;
        private List<Tag> tags;

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Paginado {
            private int cantRegistros;
            private int numTotalPag;
            private int numPagActual;
        }

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Tag {
            private String clave;
            private String valor;
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DinBody {
        private String repuestaCreacionUsuario;
        private String numeroIdentificacionUnico;
        private String fechaTransaccion;
        private String horaTransaccion;
        private String imagenAvatar;
        private String codigoAplicacionUnico;
    }
    
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DinError {
        private String tipo;
        private String fecha;
        private String origen;
        private String codigo;
        private String codigoErrorProveedor;
        private String mensaje;
        private String detalle;
    }
}