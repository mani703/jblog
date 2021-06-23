package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(PostVo vo) {
		int count = sqlSession.insert("post.insert", vo);
		return count == 1;
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("post.delete", no);
		return count == 1;
	}

	public List<PostVo> getPostList(Long categoryNo) {
		return sqlSession.selectList("post.getPostList", categoryNo);
	}

	public PostVo getPost(Long postNo) {
		return sqlSession.selectOne("post.getPost", postNo);
	}

	public Long getPostNo(Long categoryNo) {
		return sqlSession.selectOne("post.getPostNo", categoryNo);
	}
}
