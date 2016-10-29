package com.a2a.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a2a.common.Page;
import com.a2a.common.PagingInfo;
import com.a2a.message.repository.MessageRepository;

/**
 * This implementation of {@link MessageService} uses {@link MessageRepository}
 *
 */
@Service
public class RepositoryMessageService implements MessageService {

    private MessageRepository repository;
    
    /**
     * @param filter
     * @return
     * @see com.a2a.message.MessageService#filterMessages(com.a2a.message.MessageFilter, PagingInfo)
     */
    @Override
    @Transactional
    public Page<Message> filterMessages(MessageFilter filter, PagingInfo pagingInfo) {
        return repository.filter(filter, pagingInfo);
    }

    /**
     * @param id
     * @return
     * @see com.a2a.message.MessageService#fetchById(java.lang.Long)
     */
    @Override
    @Transactional
    public Message fetchById(Long id) {
        return repository.fetchById(id);
    }
    
    
    @Autowired
    public void setRepository(MessageRepository repository) {
        this.repository = repository;
    }




}
