package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> findAll(String id) {
		return categoryRepository.findAll(id);
	}

	public boolean categoryDelete(Long no) {
		return categoryRepository.delete(no);
	}

	public boolean categoryInsert(CategoryVo vo) {
		return categoryRepository.insert(vo);
	}

	public boolean insert(String id) {
		return categoryRepository.init(id);
	}

	public Long getCategoryNo(String id) {
		return categoryRepository.getCategoryNo(id);
	}
}
