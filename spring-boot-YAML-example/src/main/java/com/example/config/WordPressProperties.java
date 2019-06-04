package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.example.config.model.Menu;
import com.example.config.model.Server;
import com.example.config.model.Theme;

@Component
@ConfigurationProperties("wordpress")
public class WordPressProperties {

    private List<Menu> menus = new ArrayList<>();
    private Theme themes;
    private List<Server> servers = new ArrayList<>();
    
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public Theme getThemes() {
		return themes;
	}
	public void setThemes(Theme themes) {
		this.themes = themes;
	}
	public List<Server> getServers() {
		return servers;
	}
	public void setServers(List<Server> servers) {
		this.servers = servers;
	}
	@Override
	public String toString() {
		return "WordPressProperties [menus=" + menus + ", themes=" + themes + ", servers=" + servers + "]";
	}

}
