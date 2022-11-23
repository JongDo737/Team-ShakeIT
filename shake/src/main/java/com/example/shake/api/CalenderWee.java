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

public class CalenderWee {
    static String url = "https://open.assembly.go.kr/portal/openapi/nrsldhjpaemrmolla?UNIT_CD=100021&";

    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null)
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
                String urlFull = url + "KEY=679a42edc23e42689b7f234817f46fc6"
                        + "&pIndex=" + page
                        + "&pSize=" + 1000;
                System.out.println(urlFull);
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

                        String date = getTagValue("MEETING_DATE", eElement).substring(0, 10).replace(".", "-");
                        String title = getTagValue("SESS", eElement) + " " + getTagValue("DEGREE", eElement) + " " + getTagValue("TITLE", eElement);
                        if (date.contains("2022") && !title.contains("공청회")) {
                            calendarDto.setDate(date);
                            calendarDto.setTitle(title);
                            calendarDto.setCode(3);
                            calendarDto.setCommittee_name(getTagValue("COMMITTEE_NAME", eElement));
                            calendarDto.setTime(getTagValue("MEETING_TIME", eElement));
                            calendarDto.setUrl(getTagValue("LINK_URL2", eElement));
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