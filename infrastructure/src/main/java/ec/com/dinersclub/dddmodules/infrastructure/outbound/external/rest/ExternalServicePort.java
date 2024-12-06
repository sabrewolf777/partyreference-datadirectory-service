package ec.com.dinersclub.dddmodules.infrastructure.outbound.external.rest;

public interface ExternalServicePort {
    <T> T sendDataToExternalService(String url, T data);
}
