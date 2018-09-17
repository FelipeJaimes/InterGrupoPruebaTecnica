package com.example.android.intergrupopruebatecnica.data.mapper;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.data.remote.response.ProspectResponse;

public class ProspectMapper extends BaseMapper<ProspectResponse, Prospect> {

    public Prospect transform(ProspectResponse prospectResponse) {
        Prospect prospect = new Prospect();
        prospect.setSid(prospectResponse.getSid() == null ? "" : prospectResponse.getSid());
        prospect.setObjectId(prospectResponse.getObjectId() == null ? "" : prospectResponse.getObjectId());
        prospect.setName(prospectResponse.getName() == null ? "" : prospectResponse.getName());
        prospect.setStatusCd(prospectResponse.getStatusCd() == null ? 0 : prospectResponse.getStatusCd());
        prospect.setSurname(prospectResponse.getSurname() == null ? "" : prospectResponse.getSurname());
        prospect.setTelephone(prospectResponse.getTelephone() == null ? "" : prospectResponse.getTelephone());
        return prospect;
    }
}
