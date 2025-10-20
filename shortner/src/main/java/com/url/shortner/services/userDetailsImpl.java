package com.url.shortner.services;

import com.url.shortner.models.*;
import com.url.shortner.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;


@Data
@NoArgsConstructor
public class userDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;
	public userDetailsImpl(Long id2, String username2, String password2, List<GrantedAuthority> singletonList) {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String userName;
	private String email;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static userDetailsImpl build(User user) {
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		return new userDetailsImpl(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				Collections.singletonList(authority)
				);
	}
    
}
