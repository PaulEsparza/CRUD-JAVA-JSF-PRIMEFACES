package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import usuarioDAO.UsuarioDAO;

@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {
    private int id;
    private String alias;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private String email;
    UsuarioDAO dao = new UsuarioDAO();
    ArrayList<Usuario> lista = new ArrayList<>();

    public UsuarioBean() {
        lista = (ArrayList<Usuario>) dao.listar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public ArrayList<Usuario> getLista() {
        return lista;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void guardarUsuario() throws IOException, SQLException {
        Usuario usu = new Usuario(id, alias, nombre, apellidos, contrasena, email);
        if (dao.agrega(usu)) {
            try {
                lista = (ArrayList<Usuario>) dao.listar();
                FacesContext.getCurrentInstance().getExternalContext().redirect("Listar.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Listar.xhtml");
        }
    }

    public void consultarUsuario(int clave) throws SQLException, IOException {
        if (dao.localizaUsuario(clave)) {
            try {
                Usuario u = dao.consulta(clave);
                id = u.getId();
                alias = u.getAlias();
                nombre = u.getNombre();
                apellidos = u.getApellidos();
                contrasena = u.getContrasena();
                email = u.getEmail();
//                System.out.println("Se encontro");
//                System.out.println(id + alias + nombre + apellidos + contrasena + email);
                FacesContext.getCurrentInstance().getExternalContext().redirect("Consultar.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ocurrio un error");
        }
    }

    public void editaUsuario(int clave) throws SQLException, IOException {
        if (dao.localizaUsuario(clave)) {
            try {
                Usuario u = dao.consulta(clave);
                id = u.getId();
                alias = u.getAlias();
                nombre = u.getNombre();
                apellidos = u.getApellidos();
                contrasena = u.getContrasena();
                email = u.getEmail();
//                System.out.println("Se encontro");
//                System.out.println(id + alias + nombre + apellidos + contrasena + email);
                FacesContext.getCurrentInstance().getExternalContext().redirect("Editar.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ocurrio un error");
        }
    }

    public void editarUsuario(int id, String alias, String nombre, String apellidos, String email) throws SQLException, IOException {
//        System.out.println(id + alias + nombre + apellidos + contrasena + email);
        Usuario usu = new Usuario(id, alias, nombre, apellidos, contrasena, email);
        if (dao.modifica(usu)) {
            try {
                lista = (ArrayList<Usuario>) dao.listar();
                FacesContext.getCurrentInstance().getExternalContext().redirect("Listar.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Listar.xhtml");
        }
    }

    public void eliminaUsuario(int id) throws SQLException, IOException {
        if (dao.elimina(id)) {
            try {
                lista = (ArrayList<Usuario>) dao.listar();
                FacesContext.getCurrentInstance().getExternalContext().redirect("Listar.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Listar.xhtml");
        }
    }

    public String listar() {
        return ("Listar.xhtml");
    }

    public String crearUsuario() {
        return ("Crear.xhtml");
    }
}