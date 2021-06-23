package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("category.delete", no); 
		return count == 1;
	}

	public boolean insert(CategoryVo vo) {
		int count = sqlSession.insert("category.insert", vo);
		return count == 1;
	}

	public boolean init(String id) {
		int count = sqlSession.insert("category.init", id);
		return count == 1;
	}

	public Long getCategoryNo(String id) {
		return sqlSession.selectOne("category.getCategoryNo", id);
	}

}
