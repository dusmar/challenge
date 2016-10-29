package com.a2a.home.repository;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.a2a.common.AbstractHibernateDao;
import com.a2a.home.ChartDataItem;

@Repository
public class HibernateHomeRepository extends AbstractHibernateDao implements HomeRepository {

    private static final String SQL = "select  " + 
            "    to_char(nvl(last_attempt, created), 'YYYY-MM-DD') as \"statDate\", " + 
            "    sum(case when status = 'processed' or status = 'inhibited' then 1 else 0 end) as \"processed\", " + 
            "    sum(case when status = 'failed' then 1 else 0 end) as \"failed\", " + 
            "    sum(case when status = 'rejected' then 1 else 0 end) as \"rejected\", " + 
            "    sum(case when status not in ('rejected', 'processed', 'failed', 'inhibited') then 1 else 0 end) as \"other\" " + 
            "from " + 
            "    message " + 
            "where nvl(last_attempt, created) > (select max(nvl(created, last_attempt)) from message where nvl(created, last_attempt) < sysdate) - 7 " + 
            "group BY to_char(nvl(last_attempt, created), 'YYYY-MM-DD') " + 
            "order by 1";
    
    /**
     * @return
     * @see com.a2a.home.repository.HomeRepository#fetchChartData()
     */
    @Override
    public List<ChartDataItem> fetchChartData() {
        SQLQuery query = getSession().createSQLQuery(SQL);
        query.setResultTransformer(Transformers.aliasToBean(ChartDataItem.class));
        return query.list();
    }

}
