package biblioteca.bc;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import biblioteca.persistence.dao.UsuarioDAO;
import biblioteca.persistence.entity.Usuario;

public class UsuarioBC implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBC() {
		usuarioDAO = new UsuarioDAO();
	}

	public Usuario pesquisarPorLogin(String login) {
		if (login.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Informe o usuário para a pesquisa!"));
			return null;
		}
		return usuarioDAO.findByUserName(login);
	}

	public Boolean salvar(Usuario usuario) {
		try {
			usuario = usuarioDAO.insertOrUpdate(usuario);
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean excluir(Usuario usuario) {
		return usuarioDAO.delete(usuario);
	}
}