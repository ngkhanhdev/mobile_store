package com.example.mobilestore.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class CustomPage<T>{
    private Integer currentPage;
    private Integer totalPages;
    private Long totalItems;
    private List<T> items;

    public CustomPage(Page<T> page){
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
        this.items = page.getContent();
    }
}
