package ec.com.dinersclub.dddmodules.domain.model.profile;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecEvaluateTokenResponse {
    private TokenAssignment tokenAssignment;
    private StatusInstanceRecord statusInstanceRecord;

    // Define las clases internas o importaciones necesarias
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenAssignment {
        private RequestReference requestReference;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestReference {
        private String tokenRequestReason;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusInstanceRecord {
        private String statusType;
        private String transactionDate;
        private String status;
        private String statusCode;
        private String providerCode;
        private String message;
        private String description;
    }
} 