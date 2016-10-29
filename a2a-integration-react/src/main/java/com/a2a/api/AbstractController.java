package com.a2a.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;

import com.a2a.common.Page;
import com.a2a.common.PagingInfo;
import com.a2a.common.Utils;

/**
 * Abstract superclass for REST controllers. Implements common features
 *
 * TODO implement common features, such as paging, error handling, etc.
 *
 */
public class AbstractController {

    protected static final int DEFAULT_PAGE_SIZE = 20;

    
    protected PagingInfo readPagingInfo(RequestEntity<?> r) {
        HttpHeaders headers = r == null ? null : r.getHeaders();
        if (headers == null) {
            return new DefaultPagingInfo();
        }
        
        String s = headers.getFirst("X-Paging-Page");
        Integer page = Utils.parseInt(s, 0);
        s = headers.getFirst("X-Paging-PageSize");
        Integer pageSize = Utils.parseInt(s, 20);
        return new DefaultPagingInfo(page, pageSize);
    }
    

    protected HttpHeaders writePagingInfo(Page<?> page) {
        return writePagingInfo(new HttpHeaders(), page);
    }

    
    protected HttpHeaders writePagingInfo(HttpHeaders headers, Page<?> page) {
        if (page == null) {
            return headers;
        }

        headers.add("X-Paging", "true");
        headers.add("X-Paging-Page", String.valueOf(page.getPage()));
        headers.add("X-Paging-PagesCount", String.valueOf(page.getPagesCount()));
        
        return headers;
    }
    
    
    
    /**
     * Default implementation for {@link PagingInfo}
     *
     */
    private class DefaultPagingInfo implements PagingInfo {
        
        private int pageSize;
        
        private int page;

        public DefaultPagingInfo() {
            this(null, null);
        }
        
        public DefaultPagingInfo(Integer page, Integer pageSize) {
            super();
            this.pageSize = pageSize == null || pageSize < 1 ? 20 : pageSize;
            this.page = page == null ? 0 : page;
        }

        @Override
        public int getPageSize() {
            return pageSize;
        }

        @Override
        public int getPage() {
            return page;
        }
    }
}
