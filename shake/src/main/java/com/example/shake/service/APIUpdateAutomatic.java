package com.example.shake.service;

import com.example.shake.entity.Bill;
import com.example.shake.entity.Calendar;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface APIUpdateAutomatic {
    public String updateDataBase() throws ParserConfigurationException, IOException, SAXException;

    public List<Bill> getNotInDBBillList() throws ParserConfigurationException, IOException, SAXException;

    public  List<Calendar> getNotInDBCalendarList() throws ParserConfigurationException, IOException, SAXException;

}
