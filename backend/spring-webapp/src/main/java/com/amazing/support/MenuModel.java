package com.amazing.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "菜单模型")
public class MenuModel {
	@ApiModelProperty(value = "菜单ID", required = true)
	private Integer menuId;
	@ApiModelProperty(value = "菜单名称", required = true)
	private String menuName;
	@ApiModelProperty(value = "菜单图标样式", required = false)
	private String menuIcon;
	@ApiModelProperty(value = "父级菜单ID", required = false)
	private Integer parentMenuId;
	@ApiModelProperty(value = "菜单链接", required = false)
	private String menuUrl;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
}
