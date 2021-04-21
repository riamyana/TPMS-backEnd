package com.trasportManagement.transportservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberProof {
    private int memProofId;
    private int proofId;
    private int memberId;
    private String uidNo;
    private String proofImage;
}
