/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Role;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class RoleDBContext extends DBContext<Role> {

    public ArrayList<Role> getByUsernameAndURL(String username, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.roleid, r.rolename FROM Account a JOIN Role_Account ra on ra.username = a.username\n"
                    + "						JOIN [Role]	r on r.roleid = ra.roleid\n"
                    + "						JOIN Role_Feature rf on rf.roleid = r.roleid\n"
                    + "						JOIN Feature f on f.fid = rf.fid\n"
                    + "where a.username = ? and f.[url] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Role r = new Role();
                r.setId(rs.getInt("roleid"));
                r.setName(rs.getString("rolename"));
                roles.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
        
    }

    @Override
    public ArrayList<Role> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
