package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public boolean insertPost(PostVo vo) {
		return postRepository.insert(vo);
	}

	public boolean postDelete(Long no) {
		return postRepository.delete(no);
	}

	public List<PostVo> getPostList(Long categoryNo) {
		return postRepository.getPostList(categoryNo);
	}

	public PostVo getPost(Long postNo) {
		return postRepository.getPost(postNo);
	}

	public Long getPostNo(Long categoryNo) {
		return postRepository.getPostNo(categoryNo);
	}
}
