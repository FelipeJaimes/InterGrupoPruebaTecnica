package com.example.android.intergrupopruebatecnica.data.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<Response, Entity> {

    public abstract Entity transform(Response response);


    public List<Entity> transform(List<Response> responseList) {
        List<Entity> entityList = new ArrayList<>();
        for (Response response : responseList) {
            entityList.add(transform(response));
        }
        return entityList;
    }

}
