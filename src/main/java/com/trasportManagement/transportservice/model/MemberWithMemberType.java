package com.trasportManagement.transportservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberWithMemberType extends Member{
    private String memberTypeName;
}
