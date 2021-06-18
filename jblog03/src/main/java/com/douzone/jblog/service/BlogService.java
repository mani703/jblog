package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepositroy;
	
	public void uploadToBlog(BlogVo vo) {
		blogRepositroy.update(vo);
	}

	public String getLogo(String id) {
		return blogRepositroy.select(id);
	}

}
