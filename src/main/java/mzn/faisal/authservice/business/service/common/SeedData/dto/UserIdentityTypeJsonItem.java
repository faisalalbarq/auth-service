package mzn.faisal.authservice.business.service.common.SeedData.dto;

import mzn.faisal.authservice.data.db.base.LangAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentityTypeJsonItem {
    private Short userIdentityTypeId;
    private LangAttribute name;
}
