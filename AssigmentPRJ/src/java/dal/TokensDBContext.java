/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Tokens;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.TimeHelper;

/**
 *
 * @author ADMIN
 */
public class TokensDBContext extends DBContext<Tokens> {

    public void insertToken(Tokens entity) {
        TimeHelper helper = new TimeHelper();
        try {
            String sql = "INSERT INTO [dbo].[Tokens]\n"
                    + "           ([token]\n"
                    + "           ,[expiration_time]\n"
                    + "           ,[account])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getToken());
            stm.setDate(2, helper.convertUtilToSql(entity.getExpirationtime()) );
            stm.setString(3, entity.getAccount().getUsername());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TokensDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Tokens> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
