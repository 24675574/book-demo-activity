package com.lianggzone.activity.esdao;

import com.lianggzone.activity.entity.ActivitySearchDocument;
import com.lianggzone.activity.model.SearchParamModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h3>概要:</h3><p>ActivitySearchDocumentDao</p>
 * <h3>功能:</h3><p>ActivitySearchDocumentDao</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Repository
public class ActivitySearchDocumentDao {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 新增文档
     * @param searchDocument
     * @return
     */
    public String insertOrUpdate(ActivitySearchDocument searchDocument) {
        return this.elasticsearchTemplate.index(new IndexQueryBuilder()
                .withId(searchDocument.getId())
                .withObject(searchDocument)
                .build());
    }

    /**
     * 删除文档
     * @param objectId
     * @return
     */
    public void delete(Long objectId) {
        Criteria criteria = Criteria.where("objectId").is(objectId);
        this.elasticsearchTemplate.delete(
                new CriteriaQuery(criteria), ActivitySearchDocument.class);
    }

    /**
     * 查询文档
     * @param objectId
     * @return
     */
    public ActivitySearchDocument query(Long objectId) {
        Criteria criteria = Criteria.where("objectId").is(objectId);
        return this.elasticsearchTemplate.queryForObject(
                new CriteriaQuery(criteria), ActivitySearchDocument.class);
    }

    /**
     * 查询文档数量
     * @param searchParamModel
     * @return
     */
    public Long querySearchDocumentCount(SearchParamModel searchParamModel) {
        return this.elasticsearchTemplate.count(new NativeSearchQuery(
                this.getSearchDocumentQueryBuilder(searchParamModel)),
                ActivitySearchDocument.class);
    }

    /**
     * 查询文档列表
     * @param searchParamModel
     * @return
     */
    public List<ActivitySearchDocument> querySearchDocumentList(SearchParamModel searchParamModel) {
        // 查询文档条件封装
        SearchQuery query = new NativeSearchQuery(
                this.getSearchDocumentQueryBuilder(searchParamModel));
        query.addSort(new Sort(Sort.Direction.DESC, "prior"));
        query.setPageable(PageRequest.of(searchParamModel.getOffset(), searchParamModel.getLimit()));
        return this.elasticsearchTemplate.queryForList(query, ActivitySearchDocument.class);
    }

    /**
     * 查询文档条件封装
     * @param searchParamModel
     * @return
     */
    private QueryBuilder getSearchDocumentQueryBuilder(SearchParamModel searchParamModel) {
        BoolQueryBuilder boolQb = QueryBuilders.boolQuery();
        String key = searchParamModel.getKey();
        if(StringUtils.isNotBlank(key)){
            BoolQueryBuilder shouldQb = QueryBuilders.boolQuery();
            shouldQb.should(QueryBuilders.multiMatchQuery(key, "title", "subTitle", "introduce", "content")
                    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
            shouldQb.should(QueryBuilders.termQuery("title", key));
            shouldQb.should(QueryBuilders.termQuery("subTitle", key));
            shouldQb.should(QueryBuilders.termQuery("introduce", key));
            shouldQb.should(QueryBuilders.termQuery("content", key));
            boolQb.must(shouldQb);
        }
        return boolQb;
    }
}
