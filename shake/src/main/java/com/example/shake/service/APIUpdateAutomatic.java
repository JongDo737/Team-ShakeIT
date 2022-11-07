package com.example.shake.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface APIUpdateAutomatic {
    public String updateDataBase() throws ParserConfigurationException, IOException, SAXException;
}
