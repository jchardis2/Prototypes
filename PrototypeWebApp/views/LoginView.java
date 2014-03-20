package com.infinityappsolutions.server.proto.views;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.infinityappsolutions.server.proto.dao.DAOFactory;
import com.infinityappsolutions.server.proto.lib.actions.IASRootLoginAction;
import com.infinityappsolutions.server.proto.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.proto.lib.exceptions.DBException;
import com.infinityappsolutions.server.proto.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.proto.lib.security.SecureHashUtil;

@SessionScoped
@ManagedBean(name = "loginView")
public class LoginView implements Serializable {
	private static final long serialVersionUID = 8037321240967773536L;
	private String username;
	private String email;
	private String password;

	public LoginView() {

	}

	public LoginView(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		IASRootLoginAction action = new IASRootLoginAction(
				DAOFactory.getProductionInstance());
		try {
			SecureHashUtil hashUtil = new SecureHashUtil();
			password = hashUtil.sha256Hash((String) password);
			LoggedInUserBean liub = IASRootFacesProvider.getInstance()
					.getLoggedInUserBean();
			action.login(username, password, request, liub);
			context.getExternalContext().redirect("/user/home.xhtml");
			return "user/home.xhtml?faces-redirect=true";
		} catch (ServletException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Login failed."));
			try {
				context.getExternalContext().redirect("/login-error.xhtml");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "error";
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Login failed."));
			try {
				action.logout(request);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Login failed."));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			IASRootLoginAction action = new IASRootLoginAction(
					DAOFactory.getProductionInstance());
			action.logout(request);
			context.getExternalContext().redirect("/login.xhtml");
			return "";
		} catch (ServletException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Logout failed."));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Failed to redirect."));
		}
		return "";
	}
}
