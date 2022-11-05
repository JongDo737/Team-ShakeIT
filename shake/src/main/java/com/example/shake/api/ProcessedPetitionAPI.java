package com.example.shake.api;

import com.example.shake.dto.PendingPetitionDto;
import com.example.shake.dto.ProcessedPetitionDto;
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

public class ProcessedPetitionAPI {
    static String url = "https://open.assembly.go.kr/portal/openapi/ncryefyuaflxnqbqo?AGE=21&";
    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static List<ProcessedPetitionDto> getAPIList() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;  // 페이지 초기값
        // 총 개수 가져오기
        List<ProcessedPetitionDto> processedPetitionDtos = new ArrayList<>();
        for (int i = 1; i <= page; i++) {
            try {
                // parsing할 url 지정(API 키 포함해서)
                String urlFull = url + "KEY=679a42edc23e42689b7f234817f46fc6"
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
                    ProcessedPetitionDto processedPetitionDto = new ProcessedPetitionDto();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        processedPetitionDto.setBill_id(getTagValue("BILL_ID", eElement));
                        processedPetitionDto.setNum(getTagValue("BILL_NO", eElement));
                        processedPetitionDto.setName(getTagValue("BILL_NAME", eElement));
                        processedPetitionDto.setAge(getTagValue("AGE", eElement));
                        processedPetitionDto.setProposer(getTagValue("PROPOSER", eElement));
                        processedPetitionDto.setPro_dt(getTagValue("PROPOSE_DT", eElement));
                        processedPetitionDto.setApprover(getTagValue("APPROVER", eElement));
                        processedPetitionDto.setCommittee_id(getTagValue("CURR_COMMITTEE_ID", eElement));
                        processedPetitionDto.setPro_result(getTagValue("PROC_RESULT_CD", eElement));
                        processedPetitionDto.setCurr_committee(getTagValue("CURR_COMMITTEE", eElement));
                        processedPetitionDto.setCommittee_dt(getTagValue("COMMITTEE_DT", eElement));
                        processedPetitionDto.setUrl(getTagValue("LINK_URL", eElement));
                        processedPetitionDtos.add(processedPetitionDto);


                    }  // for end
                }  // if end

            } catch (Exception e) {
                e.printStackTrace();
            }  // try~catch end
        }
        return processedPetitionDtos;
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