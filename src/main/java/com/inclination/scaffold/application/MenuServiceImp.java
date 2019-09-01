package com.inclination.scaffold.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inclination.scaffold.api.request.menu.MenuQryByPage;
import com.inclination.scaffold.api.response.menu.MenuManagerQryResponse;
import com.inclination.scaffold.api.response.menu.MenuManagerResponse;
import com.inclination.scaffold.application.menu.MenuDto;
import com.inclination.scaffold.application.menu.MenuService;
import com.inclination.scaffold.constant.config.TException;
import com.inclination.scaffold.domain.DMenu;
import com.inclination.scaffold.infrastraction.repository.MenuResourceMapper;
import com.inclination.scaffold.infrastraction.repository.ResourceMapper;
import com.inclination.scaffold.infrastraction.repository.RoleMenuMapper;
import com.inclination.scaffold.infrastraction.repository.TmenuMapper;
import com.inclination.scaffold.infrastraction.repository.po.Tmenu;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;

@Service
public class MenuServiceImp implements MenuService{

	@Autowired
	private TmenuMapper menuMapping;
	
	/**
	 * 注入数据库服务
	 */
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	/**
	 * 注入资源的数据库服务
	 */
	@Autowired
	private ResourceMapper resourceMapper;
	
	/**
	 * 注入数据库服务
	 */
	@Autowired
	private MenuResourceMapper menuResourceMapper;
	
	@Override
	@Transactional
	public void addMenu(MenuDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DMenu.class).menuAdd(menuMapping);
	}
	@Override
	@Transactional
	public void modifyMenu(MenuDto map) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(map, DMenu.class).menuModify(menuMapping);
	}
	@Transactional
	public void deleteMenu(MenuDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DMenu.class).menuDelete(menuMapping);
	}
	public MenuManagerQryResponse findMenu(MenuQryByPage request) {
		// TODO Auto-generated method stub
		Tmenu po=ModelMapUtils.map(request, Tmenu.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<Tmenu> list=menuMapping.selectBySelective(po);
		MenuManagerQryResponse response = new MenuManagerQryResponse();
		response.setList(ModelMapUtils.map(list, MenuManagerResponse.class));
		response.PageBaseQueryEntity(request.getPage(),request.getLimit(), 
				(int)hpage.getPages(),(int)hpage.getTotal());
		return response;
	}
	@Override
	public ViewData findMenusByRoid(Integer roid) {
		// TODO Auto-generated method stub
		DMenu domain=new DMenu();
		domain.setId(roid);
		return domain.findUserMenuAndResources(roleMenuMapper,menuMapping,menuResourceMapper,resourceMapper);
		
	}
}
