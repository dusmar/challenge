package com.a2a.message.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.a2a.common.AbstractHibernateDao;
import com.a2a.common.Page;
import com.a2a.common.PagingInfo;
import com.a2a.common.QueryTokenizer;
import com.a2a.message.Message;
import com.a2a.message.MessageFilter;

@Repository
public class MessageHibernateRepository extends AbstractHibernateDao implements MessageRepository {


    /**
     * @param filter
     * @param pagingInfo
     * @return
     * @see com.a2a.message.repository.MessageRepository#filter(com.a2a.message.MessageFilter, PagingInfo)
     */
    @Override
    public Page<Message> filter(MessageFilter filter, PagingInfo pagingInfo) {
        Criteria c = filterCriteria(filter);
        
        if (pagingInfo == null) {
            return new Page<>(c.list());
        }
        
        //count
        long totalCount = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).longValue();
        
        int pageSize = pagingInfo.getPageSize();
        int pagesCount = (int) (totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0)) ;
        
        c = filterCriteria(filter);
        
        //projection for list
        String[] properties = {"id", "status", "locked", "createdBy", "creatorType", "lastAttempt", "attemptNo"};
        fields(c, Message.class, properties);
        
        c.setFirstResult(pagingInfo.getPage() * pageSize);
        c.setMaxResults(pageSize);
        return new Page<>(pagingInfo.getPage(), pagesCount, c.list());
    }


    /**
     * Prepares common criteria for filtering
     * 
     * @param filter
     * @return
     */
    protected Criteria filterCriteria(MessageFilter filter) {
        Criteria c = getSession().createCriteria(Message.class);
        
        if (filter != null) {
            List<String> tokens = QueryTokenizer.tokens(filter.getQuery());
            String[] fields = {"header", "createdBy", "creatorType", "updatedBy", "updatorType", "type"};

            Conjunction and = fulltextCriteria(tokens, fields);
            c.add(and);
        }
        
        return c;
    }


    /**
     * @param id
     * @return
     * @see com.a2a.message.repository.MessageRepository#fetchById(java.lang.Long)
     */
    @Override
    public Message fetchById(Long id) {
        if (id == null) {
            return null;
        }
        
        //TODO fetch queue?
        Criteria c = getSession().createCriteria(Message.class);
        c.add(Restrictions.idEq(id));
        return (Message) c.uniqueResult();
    }
    
}
