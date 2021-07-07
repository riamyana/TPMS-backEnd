package com.trasportManagement.transportservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProofRequirement {
    private int id;
    private int proofId;
    private String proofName;
    private int memberTypeId;
    private String memberTypeName;
}
