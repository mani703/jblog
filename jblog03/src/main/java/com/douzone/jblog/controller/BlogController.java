package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2,
			Model model) {
		
		String url = blogService.getLogo(id);
		if(url == null) {
			url = "/assets/images/20215185420898.jpg";
		}
		
		model.addAttribute("logo", url);
		
//		Long categoryNo = 0L;
//		Long postNo = 0L;
//		
//		if(pathNo2.isPresent()) {
//			categoryNo = pathNo1.get();
//			postNo = pathNo2.get();
//		} else if(pathNo1.isPresent()) {
//			categoryNo = pathNo1.get();
//		}
		
		return "blog/index";
	}
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		
		String url = blogService.getLogo(id);
		model.addAttribute("logo", url);
		return "blog/admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/basic/upload")
	public String adminUpload(
			@PathVariable("id") String id,
			@RequestParam("file") MultipartFile file,
			BlogVo vo,
			Model model) {
		
		String url = fileUploadService.restore(file);
		vo.setId(id);
		vo.setLogo(url);
		blogService.uploadToBlog(vo);
		return "redirect:/" + id + "/admin/basic";
	}
	
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(
			@PathVariable("id") String id, 
			Model model) {
		
		List<CategoryVo> list = categoryService.findAll(id);
		model.addAttribute("list", list);
		return "blog/admin/category";
	}
	
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(
			@PathVariable("id") String id, 
			CategoryVo vo) {
		
		vo.setBlogId(id);
		categoryService.categoryInsert(vo);
		return "redirect:/" + id + "/admin/category";
	}
	
	@Auth
	@RequestMapping("/{pathNo1}/admin/category/delete")
	public String adminDelete(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Long no) {
		
		postService.postDelete(no);
		categoryService.categoryDelete(no);
		return "redirect:/" + id + "/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(
			@PathVariable("id") String id,
			Model model) {
		
		List<CategoryVo> list = categoryService.findAll(id);
		model.addAttribute("list", list);
		return "blog/admin/write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write/post", method=RequestMethod.POST)
	public String adminWritePost(
			@PathVariable("id") String id,
			PostVo vo) {
		
		postService.insertPost(vo);
		return "redirect:/" + id + "/admin/write";
	}
}
