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
 * Class helps in connecting DB and UI
 */
public class UserLogin {

    People user;
    PeopleDAOImpl dao = new PeopleDAOImpl(); // DB call classs

    /*
    * Authenticate user
     */
    public People authenticateUser(String username, String password) {
        user = dao.authenticateUser(username, password); // check DB for user credentials
        return user;
    }

}
