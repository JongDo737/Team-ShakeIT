package com.example.shake.api;

import com.example.shake.dto.BillDto;
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

public class BillAPI {

    static String url ="https://open.assembly.go.kr/portal/openapi/nwbpacrgavhjryiph?AGE=21&";
    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static List<BillDto> getAPIList() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;  // 페이지 초기값
        // 총 개수 가져오기
        List<BillDto> billDtos = new ArrayList<>();
        for (int i = 1; i <= totalCount(); i++) {
            try {
                // parsing할 url 지정(API 키 포함해서)
                String urlFull = url +"KEY=679a42edc23e42689b7f234817f46fc6"
                        + "&pIndex=" + i
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

                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        System.out.println(getTagValue("ANNOUNCE_DT", eElement));
                        if(getTagValue("ANNOUNCE_DT", eElement) != null) {
                            BillDto billDto = new BillDto();
                            billDto.setDaesu(getTagValue("AGE", eElement));
                            billDto.setBill_num(getTagValue("BILL_NO", eElement));
                            billDto.setBill_name(getTagValue("BILL_NM", eElement));
                            billDto.setPropersor(getTagValue("PROPOSER", eElement));
                            billDto.setCommittee_nm(getTagValue("COMMITTEE_NM", eElement));
                            billDto.setProc_result(getTagValue("PROC_RESULT_CD", eElement));
                            billDto.setProc_date(getTagValue("PROPOSE_DT", eElement));
                            billDto.setCurr_trans_dt(getTagValue("CURR_TRANS_DT", eElement));
                            billDto.setAnnounce_dt(getTagValue("ANNOUNCE_DT", eElement));
                            billDto.setUrl(getTagValue("LINK_URL", eElement));
                            billDto.setRgs_proc_dt(getTagValue("RGS_PROC_DT", eElement));
                            billDto.setBill_id(getTagValue("BILL_ID", eElement));
                            billDto.setCurr_committee_id(getTagValue("CURR_COMMITTEE_ID", eElement));

                            billDtos.add(billDto);
                        }




                    }  // for end
                }  // if end

            } catch (Exception e) {
                e.printStackTrace();
            }  // try~catch end
        }
        return billDtos;
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