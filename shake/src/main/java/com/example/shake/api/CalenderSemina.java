package com.example.shake.api;

import com.example.shake.dto.CalendarDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalenderSemina {
    static String url = "https://open.assembly.go.kr/portal/openapi/nfcoioopazrwmjrgs?";
    static String[] nowDate = Date.getYearMonth().split("-");

    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static List<CalendarDto> getAPIList() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;  // 페이지 초기값
        // 총 개수 가져오기
        List<CalendarDto> calendarDtos = new ArrayList<>();
        for (int i = 1; i <= page; i++) {
            try {
                // parsing할 url 지정(API 키 포함해서)
                String urlFull = url +"KEY=679a42edc23e42689b7f234817f46fc6"
                        + "&pIndex=" + page
                        + "&pSize=" + 1000;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(urlFull);

                // root tag
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("row");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    CalendarDto calendarDto = new CalendarDto();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
//                    System.out.println("MEETINGSESSION  : " +getTagValue("MEETINGSESSION", eElement)+" "+getTagValue("CHA", eElement));

                        String date = getTagValue("SDATE", eElement).replace(".", "-");
                        String[] schedules = date.split("-");
                        int nowMonth = Integer.parseInt(nowDate[1]);
                        int scheduleMonth = Integer.parseInt(schedules[1]);
                        // 2022년이고 한달 전꺼부터 들고옴
                        if (nowDate[0].equals(schedules[0]) && nowMonth<=scheduleMonth) {
                            calendarDto.setDate(date);
                            calendarDto.setTitle(getTagValue("TITLE", eElement));
                            calendarDto.setCommittee_name(getTagValue("DESCRIPTION", eElement));
                            calendarDto.setCode(1);
                            calendarDto.setTime(getTagValue("STIME", eElement));
                            calendarDto.setUrl(getTagValue("LINK", eElement));
                            calendarDto.setCreate_date(Date.getDate());
                            calendarDto.setUpdate_date(Date.getDate());
                            calendarDtos.add(calendarDto);
                        }

                    }  // for end
                }  // if end

            } catch (Exception e) {
                e.printStackTrace();
            }  // try~catch end
        }
        return calendarDtos;
    }  // main end
    public static int totalCount() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;
        String urlFull = url +
                "KEY=679a42edc23e42689b7f234817f46fc6"
                        + "&pIndex=" + page
                        + "&pSize=1";

        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(urlFull);

        // root tag
        doc.getDocumentElement().normalize();

        // 국회의원 수가 있는 head 태그
        NodeList countList = doc.getElementsByTagName("head");
        // head 태그 안 총 수 출력
        int totalCount = Integer.parseInt(getTagValue("list_total_count", (Element) ((Node) countList.item(0))));
        System.out.println("총 데이터 수 : " + totalCount);
        return totalCount / 1000 + 1;
    }
}  // class end