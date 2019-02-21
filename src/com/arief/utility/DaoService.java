/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arief.utility;

import java.util.List;

/**
 *
 * @author Arief Kurniawan 1772049
 * @param <Object>
 */
public interface DaoService<Object> {

    public int addData(Object object);

    public int updateData(Object object);

    public int deleteData(Object object);

    public List<Object> showAllData();

    public Object getOneData(Object object);
}
