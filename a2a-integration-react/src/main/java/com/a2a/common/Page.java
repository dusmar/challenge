package com.a2a.common;

import java.util.Collections;
import java.util.List;

public class Page<T> {

    private List<T> content;
    
    private int pagesCount;
    
    private int page;

    
    public Page(List<T> content) {
        this(0, 1, content);
    }

    public Page(int page, int pagesCount, List<T> content) {
        this.content = content;
        this.page = page;
        this.pagesCount = pagesCount;
    }

    public List<T> getContent() {
        return content == null ? Collections.emptyList() : content;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getPage() {
        return page;
    }

}
