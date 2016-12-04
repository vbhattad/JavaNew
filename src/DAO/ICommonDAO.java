/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 * Interface to create Table and Drop Table.
 * @author katha
 */
public interface ICommonDAO extends AutoCloseable{

    /**
     * 
     */
    void createTable();

    /**
     *
     */
    void dropTable();
}
