package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.CategoryVo;

@RestController("blogControllerApi")
@RequestMapping("/{id:(?!assets).*}/category/api")
public class BlogController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping("")
	public JsonResult read(@PathVariable String id) {
		List<CategoryVo> list = categoryService.findAll(id);
		return JsonResult.success(list);
	}
	
	@PostMapping("")
	public JsonResult create(
			@PathVariable String id,
			@RequestBody CategoryVo vo) {
		
		vo.setBlogId(id);
		categoryService.categoryInsert(vo);
		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/{pathNo1}")
	public JsonResult delete(@PathVariable("pathNo1") Long no) {
		postService.postDelete(no);
		Long data = categoryService.categoryDelete(no) ? no : -1L;
		System.out.println(no);
		return JsonResult.success(data);
	}
}
