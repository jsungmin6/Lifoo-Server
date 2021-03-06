package ga.lifoo.util;

import java.util.*;

public class RandomNickname {
    private static ArrayList<String> adjective = new ArrayList<String>(
            Arrays.asList(
                    "맛있는",
                    "맛없는",
                    "달콤한",
                    "뜨거운",
                    "차가운",
                    "보통",
                    "박살난",
                    "그냥",
                    "매운",
                    "귀여운",
                    "최고의",
                    "기막힌",
                    "양산형",
                    "엄마표",
                    "더러운",
                    "깨끗한",
                    "엎어진",
                    "완벽한",
                    "창렬한",
                    "비싼",
                    "추억의",
                    "구수한",
                    "시원한",
                    "따뜻한",
                    "초라한",
                    "짭짤한",
                    "시큼한",
                    "칠성급",
                    "아빠표",
                    "집나간",
                    "우주의",
                    "주워온",
                    "발로찬",
                    "남극의",
                    "한국의",
                    "길바닥",
                    "버려진",
                    "토해낸",
                    "미국식",
                    "유럽식",
                    "일본식",
                    "중국식",
                    "깨끗한",
                    "타버린",
                    "얼린",
                    "일년전"
            ));

    private static ArrayList<String> food = new ArrayList<String>(
            Arrays.asList(
                    "라면",
                    "감자",
                    "만두",
                    "앙버터",
                    "마카롱",
                    "파스타",
                    "피자",
                    "해물찜",
                    "조개탕",
                    "연어회",
                    "연어",
                    "꽃게탕",
                    "짬뽕",
                    "떡라면",
                    "볶음밥",
                    "김치전",
                    "쌈밥",
                    "호박전",
                    "케이크",
                    "젤리",
                    "초콜릿",
                    "사탕",
                    "빼빼로",
                    "튀김",
                    "붕어빵",
                    "떡볶이",
                    "순대",
                    "어묵",
                    "삼겹살",
                    "미나리",
                    "성게",
                    "도시락",
                    "과자",
                    "토스트",
                    "브리또",
                    "밥버거",
                    "햄버거",
                    "왕족발",
                    "알사탕",
                    "시금치",
                    "토란",
                    "고등어",
                    "꽁치",
                    "번데기",
                    "카카오",
                    "커피",
                    "흰쌀밥",
                    "잡곡밥"
            ));

    private static ArrayList<String> getAdjective() {
        return adjective;
    }


    private static ArrayList<String> getFood() {
        return food;
    }

    public static String makeNickName(){
        ArrayList<String> adjective = getAdjective();
        ArrayList<String> food = getFood();

        int adRandomIdx = (int)(Math.random() * adjective.size());
        int foodRandomIdx = (int)(Math.random() * food.size());

        return adjective.get(adRandomIdx) + " " + food.get(foodRandomIdx);

    }


}
