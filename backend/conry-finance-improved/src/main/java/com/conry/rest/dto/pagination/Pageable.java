package com.conry.rest.dto.pagination;

import com.conry.commons.Pagination;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.QueryParam;

@Setter
@Getter
public class Pageable {

    @QueryParam("page")
    private int page = 0;
    @QueryParam("size")
    private int size = Pagination.DEFAULT_PAGINATION_SIZE;
    @QueryParam("sort")
    private String sort = "id";
}
