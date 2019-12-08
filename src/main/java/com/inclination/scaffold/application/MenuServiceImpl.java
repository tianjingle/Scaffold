package com.inclination.scaffold.application;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.infrastraction.repository.*;
import com.inclination.scaffold.infrastraction.repository.po.MenuPo;
import com.inclination.scaffold.utils.ViewData;
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
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DMenu;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewDataOld;
import tk.mybatis.mapper.entity.Example;

@Service
public class MenuServiceImpl implements MenuService{

    /**
     * 菜单
     */
	@Autowired
	private MenuPoMapper menuMapping;
	/**
	 * 注入数据库服务
	 */
	@Autowired
	private RoleMenuPoMapper roleMenuMapper;
	
	/**
	 * 注入资源的数据库服务
	 */
	@Autowired
	private ResourcePoMapper resourceMapper;
	
	/**
	 * 注入数据库服务
	 */
	@Autowired
	private MenuResourcePoMapper menuResourceMapper;
	
	@Override
	@Transactional
	public void addMenu(MenuDto dto) throws TException {
		ModelMapUtils.map(dto, DMenu.class).menuAdd(menuMapping);
	}
	@Override
	@Transactional
	public void modifyMenu(MenuDto map) throws TException {
		ModelMapUtils.map(map, DMenu.class).menuModify(menuMapping);
	}
	@Override
    @Transactional
	public void deleteMenu(MenuDto dto) throws TException {
		ModelMapUtils.map(dto, DMenu.class).menuDelete(menuMapping);
	}
	@Override
	public ViewData findMenu(MenuQryByPage request) {
		MenuPo po=ModelMapUtils.map(request, MenuPo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<MenuPo> list=menuMapping.selectBySelective(po);
		return ViewData.success(ModelMapUtils.map(list, MenuManagerResponse.class),hpage.getPages(),hpage.getTotal());
	}
	@Override
	public ViewData findMenusByRoid(Integer roid) {
		DMenu domain=new DMenu();
		domain.setId(roid);
		return domain.findUserMenuAndResources(roleMenuMapper,menuMapping,menuResourceMapper,resourceMapper);
		
	}

	@Override
	@Transactional
	public ViewData menuBatchRemove(String ids) throws TException {
		Example example=new Example(MenuPo.class);
		example.createCriteria().andIn("id", Arrays.asList(ids.split(",")));
		if(menuMapping.deleteByExample(example)<1){
			throw new TException(TErrorCode.ERROR_DELETE_MENU_CODE,TErrorCode.ERROR_DELETE_MENU_MSG);
		}
		return ViewData.success(true);
	}
}
