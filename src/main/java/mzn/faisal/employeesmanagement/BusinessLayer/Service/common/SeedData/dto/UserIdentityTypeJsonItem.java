package mzn.faisal.employeesmanagement.BusinessLayer.Service.common.SeedData.dto;

import mzn.faisal.employeesmanagement.DataLayer.db.base.LangAttribute;
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
