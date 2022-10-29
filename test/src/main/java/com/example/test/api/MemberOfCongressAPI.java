
package com.example.test.api;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MemberOfCongressAPI {

    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static void getAPIList() {
        int page = 1;  // 페이지 초기값
        try{
//            while(true){
            // parsing할 url 지정(API 키 포함해서)
            String url = "https://open.assembly.go.kr/portal/openapi/nwvrqwxyaytdsfvhu";

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // root tag
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("row");
            //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;
                    System.out.println("######################");
                    //System.out.println(eElement.getTextContent());
                    System.out.println("이름  : " +getTagValue("HG_NM", eElement));
                    System.out.println("한자이름  : " +getTagValue("HJ_NM", eElement));
                    System.out.println("영어이름 : " +getTagValue("ENG_NM", eElement));
                    System.out.println("양/음력 : " +getTagValue("BTH_GBN_NM", eElement));
                    System.out.println("생년월일  : " +getTagValue("BTH_DATE", eElement));
                    System.out.println("모름  : " +getTagValue("JOB_RES_NM", eElement));
                    System.out.println("당 : " +getTagValue("POLY_NM", eElement));
                    System.out.println("지역  : " +getTagValue("ORIG_NM", eElement));
                    System.out.println("뭐하는지  : " +getTagValue("ELECT_GBN_NM", eElement));
                    System.out.println("의원명  : " +getTagValue("CMIT_NM", eElement));
                    System.out.println("재임기간 : " +getTagValue("CMITS", eElement));
                    System.out.println("몇 회차 재임  : " +getTagValue("REELE_GBN_NM", eElement));

                    System.out.println("의안ID  : " +getTagValue("UNITS", eElement));
                    System.out.println("의원명  : " +getTagValue("SEX_GBN_NM", eElement));
                    System.out.println("재임기간 : " +getTagValue("TEL_NO", eElement));
                    System.out.println("몇 회차 재임  : " +getTagValue("E_MAIL", eElement));
                    System.out.println("의안ID  : " +getTagValue("BTH_DATE", eElement));
                    System.out.println("의원명  : " +getTagValue("STAFF", eElement));
                    System.out.println("재임기간 : " +getTagValue("SECRETARY", eElement));
                    System.out.println("몇 회차 재임  : " +getTagValue("SECRETARY2", eElement));
                    System.out.println("의안ID  : " +getTagValue("MONA_CD", eElement));
                    System.out.println("의원명  : " +getTagValue("MEM_TITLE", eElement));
                    System.out.println("재임기간 : " +getTagValue("ASSEM_ADDR", eElement));
                }  // for end
            }  // if end

//                page += 1;
//                System.out.println("page number : "+page);
//                if(page > 12){
//                    break;
//                }
//            }  // while end

        } catch (Exception e){
            e.printStackTrace();
        }  // try~catch end
    }  // main end
}  // class end