package com.gamza.moulle.service;

import com.gamza.moulle.dto.SearchDetailDto;
import com.gamza.moulle.dto.SearchDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import reactor.core.publisher.Mono;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AladinSearchService {

    private final WebClient webClient;
    private static final String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";

    @Value("${aladin.key}")
    private String ttbKey;

    public AladinSearchService() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public Mono<SearchDetailDto> getBookByQuery(String isbn) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("ttbkey", ttbKey)
                        .queryParam("itemIdType", "ISBN")
                        .queryParam("output", "xml")
                        .queryParam("Version", "20131101")
                        .queryParam("OptResult", "ebookList,usedList,reviewList")
                        .queryParam("ItemId", isbn)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(xml -> {
                    try {
                        return parseXmlData(xml);
                    } catch (ParserConfigurationException | IOException | SAXException e) { // 오류처리 설정해주고
                        throw new RuntimeException(e);
                    }
                });
    }

    public SearchDetailDto parseXmlData(String xml) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); // 빌더 팩토리 생성
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        Document document = builder.parse(inputSource); // 여기까지는 xml 데이터 빌더 생성하고 파싱하는 과정

        SearchDetailDto searchDetailDto = new SearchDetailDto();
        List<Map<String, String>> itemList = new ArrayList<>(); // 하나씩 추가하긴 번거로우니까 List형식으로 필요한 데이터를 리스트에 Map을 이용해서 추가해주면 뽑히도록해보자


        NodeList itemNodes = document.getElementsByTagName("object");
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Node itemNode = itemNodes.item(i);
            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element itemElement = (Element) itemNode;

                Map<String, String> itemData = new HashMap<>();
                itemData.put("title", getTextContent(itemElement, "title")); // 이거는 title이 두개라서 index 0번째가 아니라 1번째를 꺼내야할거같은데 흠,,
                itemData.put("author", getTextContent(itemElement, "author"));
                itemData.put("description", getTextContent(itemElement, "description"));
                itemData.put("isbn", getTextContent(itemElement, "isbn"));
                itemData.put("priceStandard", getTextContent(itemElement, "priceStandard")); // 가격도 우선 text 형식으로 던져지네
                // 필요한 데이터 있을떄마다 여기다가 추가하면됨

                itemList.add(itemData);
            }
        }
        searchDetailDto.setItems(itemList);
        return searchDetailDto;
    }

    private String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            if (tagName == "title") {
                return nodeList.item(1).getTextContent(); // title은 두개라서 index 1번째를 하면 된다.
            }
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}
