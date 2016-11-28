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
public class UserSignUp {
    
    People user;
    PeopleDAOImpl dao = new PeopleDAOImpl();
    
    public boolean addUser(String firstName, String lastName, String password, String andrewId,char userType) {
        user = new People(firstName, lastName, password, andrewId, userType);
        System.out.println("Here! UserSignUp");
        return dao.addUser(user);
    }
    
}
