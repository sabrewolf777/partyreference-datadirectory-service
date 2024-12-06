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
public class RecRegisterPartyReferenceDataDirectoryEntryRs {
    private PartyReferenceDataDirectoryEntry partyReferenceDataDirectoryEntry;
    private DocumentInstanceRecord documentInstanceRecord;
    private String applicationCode;
    private StatusInstanceRecord statusInstanceRecord;

    // Clases internas
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyReferenceDataDirectoryEntry {
        private String directoryEntryStatus;
        private PartyReference partyReference;
        private DirectoryEntryDate directoryEntryDate;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyReference {
        private String referenceId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DirectoryEntryDate {
        private String directoryEntryValueDate;
        private String directoryEntryValueTime;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DocumentInstanceRecord {
        private String documentContent;
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
