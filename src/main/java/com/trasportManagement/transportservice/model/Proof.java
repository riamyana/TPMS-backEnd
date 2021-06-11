package com.trasportManagement.transportservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Proof {
    private int proofId;
    private String proofName;
    private String memberTypeName;
    private int memberTypeId;
}
