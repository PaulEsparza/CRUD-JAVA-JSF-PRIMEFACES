package usuarioDAO;

import BaseDeDatos.Conexion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

public class UsuarioDAO {
    Conexion cnx = new Conexion();
    Usuario u = new Usuario();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        ArrayList<Usuario> list = new ArrayList<>();
        String sql = "SELECT * FROM tblUsuario";
        try {
            con = cnx.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id"));
                usu.setAlias(rs.getString("alias"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setContrasena(rs.getString("contrasena"));
                usu.setEmail(rs.getString("email"));
                list.add(usu);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Usuario consulta(int id) throws SQLException {
        String sql = "SELECT * FROM tblUsuario WHERE id=" + id;
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            con = cnx.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setAlias(rs.getString("alias"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setContrasena(rs.getString("contrasena"));
                u.setEmail(rs.getString("email"));
            }
            sessionMapObj.put("editRecordObj", u);
        } catch (Exception e) {

        }
        return u;
    }

    public boolean localizaUsuario(int id) {
        String sql = "SELECT * FROM tblUsuario WHERE id=" + id;
        try {
            con = cnx.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean agrega(Usuario u) throws SQLException {
        String sql = "INSERT INTO tblUsuario(alias,nombre,apellidos,contrasena,email)"
                + " VALUES('" + u.getAlias() + "','" + u.getNombre() + "','"
                + u.getApellidos() + "','" + u.getContrasena() + "','" + u.getEmail() + "')";
        try {
            con = cnx.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    public boolean modifica(Usuario u) throws SQLException {
        String sql = "UPDATE tblUsuario SET alias='" + u.getAlias() + "',nombre='"
                + u.getNombre() + "',apellidos='" + u.getApellidos()
                + "',contrasena='" + u.getContrasena() + "',email='" + u.getEmail() + "' "
                + "WHERE id=" + u.getId();
        try {
            con = cnx.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    public boolean elimina(int id) throws SQLException {
        String sql = "DELETE FROM tblUsuario WHERE id=" + id;
        try {
            con = cnx.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }
}