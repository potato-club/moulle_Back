package com.gamza.moulle.service;

import com.gamza.moulle.dto.aladin.SearchDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AladinSearchService {

    private final WebClient aladinWebClient;

    @Value("${aladin.key}")
    private String ttbKey;

    public Mono<SearchDetailDto> getBookByQuery(String isbn) {
        return aladinWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("ttbkey", ttbKey)
                        .queryParam("itemIdType", "ISBN13")
                        .queryParam("output", "JS")
                        .queryParam("Version", "20131101")
                        .queryParam("OptResult", "ebookList,ratingInfo,reviewList")
                        .queryParam("ItemId", isbn)
                        .build())
                .retrieve()
                .bodyToMono(SearchDetailDto.class);
    }
//
//    public SearchDetailDto parseXmlData(String xml) throws ParserConfigurationException, IOException, SAXException {
//
//        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); // 빌더 팩토리 생성
//        DocumentBuilder builder = builderFactory.newDocumentBuilder();
//        InputSource inputSource = new InputSource(new StringReader(xml));
//        Document document = builder.parse(inputSource); // 여기까지는 xml 데이터 빌더 생성하고 파싱하는 과정
//
//        SearchDetailDto searchDetailDto = new SearchDetailDto();
//        List<Map<String, String>> itemList = new ArrayList<>(); // 하나씩 추가하긴 번거로우니까 List형식으로 필요한 데이터를 리스트에 Map을 이용해서 추가해주면 뽑히도록해보자
//
//
//        NodeList itemNodes = document.getElementsByTagName("object");
//        for (int i = 0; i < itemNodes.getLength(); i++) {
//            Node itemNode = itemNodes.item(i);
//            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element itemElement = (Element) itemNode;
//
//                Map<String, String> itemData = new HashMap<>();
//                itemData.put("title", getTextContent(itemElement, "title")); // 이거는 title이 두개라서 index 0번째가 아니라 1번째를 꺼내야할거같은데 흠,,
//                itemData.put("author", getTextContent(itemElement, "author"));
//                itemData.put("description", getTextContent(itemElement, "description"));
//                itemData.put("isbn", getTextContent(itemElement, "isbn"));
//                itemData.put("priceStandard", getTextContent(itemElement, "priceStandard")); // 가격도 우선 text 형식으로 던져지네
//                // 필요한 데이터 있을떄마다 여기다가 추가하면됨
//
//                itemList.add(itemData);
//            }
//        }
//        searchDetailDto.setItems(itemList);
//        return searchDetailDto;
//    }
//
//    private String getTextContent(Element element, String tagName) {
//        NodeList nodeList = element.getElementsByTagName(tagName);
//        if (nodeList.getLength() > 0) {
//            if (tagName == "title") {
//                return nodeList.item(1).getTextContent(); // title은 두개라서 index 1번째를 하면 된다.
//            }
//            return nodeList.item(0).getTextContent();
//        }
//        return "";
//    }
}
