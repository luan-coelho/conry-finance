package com.conry.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public static final int DEFAULT_PAGINATION_SIZE = 1;

    public static <T> Pagination<T> of(List<T> content, int size, long totalElements) {
        Pagination<T> paginationInstance = new Pagination<>();
        paginationInstance.setContent(content);
        paginationInstance.setSize(size);
        paginationInstance.setTotalElements(totalElements);
        paginationInstance.setTotalPages((int) (totalElements / size));

        return paginationInstance;
    }

    public static <T> Pagination<T> of(List<T> content, int page, int size, long totalElements) {
        Pagination<T> paginationInstance = new Pagination<>();
        paginationInstance.setContent(content);
        paginationInstance.setPage(page);
        paginationInstance.setSize(size);
        paginationInstance.setTotalElements(totalElements);
        paginationInstance.setTotalPages((int) (totalElements / size));

        return paginationInstance;
    }
}
