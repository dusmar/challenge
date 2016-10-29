package com.a2a.message;

import com.a2a.common.Page;
import com.a2a.common.PagingInfo;

public interface MessageService {

    /**
     * Returns list of messages that match provided filter criteria
     * 
     * @param filter
     * @param pagingInfo
     * @return
     */
    Page<Message> filterMessages(MessageFilter filter, PagingInfo pagingInfo);
    
    
    /**
     * Returns message
     * 
     * @param id
     * @return
     */
    Message fetchById(Long id);
}
