package com.example.fooddatastoragecalc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;

public class JsoupTest {
    public static void main(String[] args) throws InterruptedException {
        Data data1 = new Data();

        String[] searchlist = data1.mydata;

        for(String data:searchlist){
            getImage(data);
            Thread.sleep(10);
        }
    }
    public static void getImage(String searchQuery){
        try {
            String googleImageUrl = "https://www.google.com/search?q=" + searchQuery + "&tbm=isch";

            // Google 이미지 검색 페이지에 HTTP GET 요청 보내기
            Document doc = Jsoup.connect(googleImageUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();

            // 이미지 URL을 포함한 엘리먼트 선택
            Elements imgElements = doc.select("img[data-src]");

            if (!imgElements.isEmpty()) {
                // 첫 번째 이미지 URL 가져오기
                Element firstImgElement = imgElements.first();
                String imageUrl = firstImgElement.attr("data-src");

                // 이미지 URL을 출력
                System.out.println(imageUrl);

                // 이후에 데이터베이스에 이미지 URL을 저장하는 로직을 추가하세요.
            } else {
                System.out.println("이미지를 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}