package com.cyou.laifou.lucene.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.cyou.laifou.lucene.model.LuceneIndexTask;


public interface LuceneIndexTaskDAO {

public static final String ALIGNNAME ="alias_lucene_index_task0";

public static final String VIEW ="alias_lucene_index_task0.`id`, alias_lucene_index_task0.`object_id` as objectId, alias_lucene_index_task0.`object_type` as objectType, alias_lucene_index_task0.`status`, alias_lucene_index_task0.`option`, alias_lucene_index_task0.`add_time` as addTime, alias_lucene_index_task0.`handle_time` as handleTime";

public static final String TABLENAME ="lucene_index_task";

	/** insert LuceneIndexTask */
	@Insert("insert into lucene_index_task (`object_id`, `object_type`, `status`, `option`, `add_time`, `handle_time`) values (#{objectId}, #{objectType}, #{status}, #{option}, #{addTime}, #{handleTime})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id", useCache = false, flushCache = true)
	public Long insert(LuceneIndexTask luceneIndexTask);
	
	
	/** insert LuceneIndexTask */
    @Insert("insert ignore into lucene_index_task (`object_id`, `object_type`, `status`, `option`, `add_time`, `handle_time`) values (#{objectId}, #{objectType}, #{status}, #{option}, #{addTime}, #{handleTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id", useCache = false, flushCache = true)
    public Long insertByIgnore(LuceneIndexTask luceneIndexTask);

}
