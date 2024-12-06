package ec.com.dinersclub.dddmodules.domain.model.user;

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
public class RecRegisterPartyReferenceDataDirectoryEntryRq {
    private String username;
    private PasswordInstanceRecord passwordInstanceRecord;
    private PartyReferenceDataDirectoryEntry partyReferenceDataDirectoryEntry;
    private String temporaryUserName;
    private Association[] associations;
    private String authenticationMethod;
    private TokenAssignment tokenAssignment;
    private BiometricInstanceRecord biometricInstanceRecord;
    private PartyLegalEntityReference partyLegalEntityReference;
    private String sourceRequest;

    // Clases internas
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PasswordInstanceRecord {
        private String authenticationPasswordStoredValue;
        private String authenticationMobilePassword;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyReferenceDataDirectoryEntry {
        private PartyReference partyReference;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyReference {
        private Identification[] identifications;
        private PartyProfile partyProfile;
        private String partyType;
        private ContactPoint[] contactPoint;
        private boolean corporateCustomer;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Identification {
        private Identifier identifier;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Identifier {
        private String identifierValue;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyProfile {
        private String investmentNumber;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactPoint {
        private String contactPointValue;
        private String contactPointType;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Association {
        private ProductInstanceRecord productInstanceRecord;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductInstanceRecord {
        private PaymentCard paymentCard;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentCard {
        private String cardNumber;
        private String cardExpiryDate;
        private String cardSecurityCode;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenAssignment {
        private boolean previousToken;
        private TokenIdentificationCode tokenIdentificationCode;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenIdentificationCode {
        private IdentifierValue identifierValue;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdentifierValue {
        private String value;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BiometricInstanceRecord {
        private String authenticationBiometricRecord;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyLegalEntityReference {
        private Organisation organisation;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Organisation {
        private OrganisationIdentification[] organisationIdentification;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrganisationIdentification {
        private Identifier identifier;
    }
}
