package com.example.shake.api;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.example.shake.dto.CongressOfMemberDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberOfCongressAPI {

    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static List<CongressOfMemberDto> getAPIList() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;  // 페이지 초기값
        List<CongressOfMemberDto> totalList = new ArrayList<>();

        try{
//            while(true){
            // parsing할 url 지정(API 키 포함해서)
            String url = "https://open.assembly.go.kr/portal/openapi/nwvrqwxyaytdsfvhu?"
                    +"KEY=679a42edc23e42689b7f234817f46fc6"
                    +"&pIndex="+page
                    +"&pSize="+299;
//https://open.assembly.go.kr/portal/openapi/nubbgpxmawmzkclkc?KEY=679a42edc23e42689b7f234817f46fc6&pIndex=1&pSize=50

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // root tag
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("row");
            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    CongressOfMemberDto congressOfMemberDto = new CongressOfMemberDto();
                    Element eElement = (Element) nNode;
                    System.out.println("######################");
                    System.out.println(getTagValue("HG_NM", eElement));
                    congressOfMemberDto.setHG_NM(getTagValue("HG_NM", eElement));
                    congressOfMemberDto.setHJ_NM(getTagValue("HJ_NM", eElement));
                    congressOfMemberDto.setCMITS(getTagValue("HG_NM", eElement));
                    congressOfMemberDto.setENG_NM(getTagValue("ENG_NM", eElement));
                    congressOfMemberDto.setBTH_GBN_NM(getTagValue("BTH_GBN_NM", eElement));
                    congressOfMemberDto.setBTH_DATE(getTagValue("BTH_DATE", eElement));
                    congressOfMemberDto.setJOB_RES_NM(getTagValue("JOB_RES_NM", eElement));
                    congressOfMemberDto.setPOLY_NM(getTagValue("POLY_NM", eElement));
                    congressOfMemberDto.setORIG_NM(getTagValue("ORIG_NM", eElement));
                    congressOfMemberDto.setELECT_GBN_NM(getTagValue("ELECT_GBN_NM", eElement));
                    congressOfMemberDto.setCMIT_NM(getTagValue("CMIT_NM", eElement));
                    congressOfMemberDto.setCMITS(getTagValue("CMITS", eElement));
                    congressOfMemberDto.setREELE_GBN_NM(getTagValue("REELE_GBN_NM", eElement));
                    congressOfMemberDto.setUNITS(getTagValue("UNITS", eElement));
                    congressOfMemberDto.setSEX_GBN_NM(getTagValue("SEX_GBN_NM", eElement));
                    congressOfMemberDto.setTEL_NO(getTagValue("TEL_NO", eElement));
                    congressOfMemberDto.setE_MAIL(getTagValue("E_MAIL", eElement));
                    congressOfMemberDto.setHOMEPAGE(getTagValue("HOMEPAGE", eElement));
                    congressOfMemberDto.setSTAFF(getTagValue("STAFF", eElement));
                    congressOfMemberDto.setSECRETARY(getTagValue("SECRETARY", eElement));
                    congressOfMemberDto.setSECRETARY2(getTagValue("SECRETARY2", eElement));
                    congressOfMemberDto.setMONA_CD(getTagValue("MONA_CD", eElement));
                    congressOfMemberDto.setMEM_TITLE(getTagValue("MEM_TITLE", eElement));
                    congressOfMemberDto.setASSEM_ADDR(getTagValue("ASSEM_ADDR", eElement));
                    congressOfMemberDto.setImg_URL(getMemberURL(temp));
                    totalList.add(congressOfMemberDto);

                }  // for end
            }  // if end

        } catch (Exception e){
            e.printStackTrace();
        }  // try~catch end
        return totalList;
    }  // main end
    public static String getMemberURL(int pageNum){
        String img="";
        // 본인이 받은 api키를 추가
        String key = "LQpoWBF5iUvcdKuhaSh0T4CTW%2BuU9UFhw3preDYbsM2LV1%2F9TLBF3Bn4hiy6%2B0mZzgOHHm8sVUolWrMitk%2FYuQ%3D%3D";
//&numOfRows=10&pageNo=1
        try{
            // parsing할 url 지정(API 키 포함해서)
            String i = (pageNum + 1)+"";
            String url = "http://apis.data.go.kr/9710000/NationalAssemblyInfoService/getMemberCurrStateList?" +
                    "serviceKey="+key+
                    "&numOfRows=1" +
                    "&pageNo=" + i;
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // 제일 첫번째 태그
            doc.getDocumentElement().normalize();

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("item");

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);

                Element eElement = (Element) nNode;
                System.out.println(getTagValue("empNm", eElement)+"의원사진 : " +getTagValue("jpgLink", eElement));
                img = getTagValue("jpgLink", eElement);

            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }


}  // class end