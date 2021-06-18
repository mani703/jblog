package com.douzone.jblog.service;

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
}
