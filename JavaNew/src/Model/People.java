/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author katha
 */
public class People {

    public People(String firstName, String lastName, String password, String andrewId, char memberType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.andrewId = andrewId;
        this.memberType = memberType;
        //this.courseType = courseType;
    }
    
    public People(){}
    
    private String firstName;
    private String lastName;
    private String password;
    private String andrewId;
    private char memberType;
    private char courseType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAndrewId() {
        return andrewId;
    }

    public void setAndrewId(String andrewId) {
        this.andrewId = andrewId;
    }

    public char getMemberType() {
        return memberType;
    }

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public char getCourseType() {
        return courseType;
    }

    public void setCourseType(char courseType) {
        this.courseType = courseType;
    }
}
