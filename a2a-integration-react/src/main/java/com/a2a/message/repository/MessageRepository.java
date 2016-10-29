package com.a2a.message.repository;

import com.a2a.common.Page;
import com.a2a.common.PagingInfo;
import com.a2a.message.Message;
import com.a2a.message.MessageFilter;


public interface MessageRepository {

    Page<Message> filter(MessageFilter filter, PagingInfo pagingInfo);
    
    Message fetchById(Long id);
    
}
