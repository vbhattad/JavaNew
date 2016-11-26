/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginAndSignup;

import Model.People;
import DAO.PeopleDAOImpl;

/**
 *
 * @author katha
 */
public class UserLogin {

    People user;
    PeopleDAOImpl dao;

    public char authenticateUser(String username, String password) {
        user = dao.authenticateUser(username, password); // check DB for user credentials
        char memberType = 'I'; // I is for Invalid Member
        if (!user.getAndrewId().isEmpty()) { // If andrew id is empty, authentication failed
            // Aunthentication successful. 
            memberType = user.getMemberType();
        }
        return memberType;
    }
    
    
    
}
