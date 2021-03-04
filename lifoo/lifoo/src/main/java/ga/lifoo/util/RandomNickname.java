package ga.lifoo.util;

import java.util.*;

public class RandomNickname {
    private static ArrayList<String> adjective = new ArrayList<String>(
            Arrays.asList("맛있는",
                    "맛없는",
                    "달콤한",
                    "뜨거운",
                    "차가운",
                    "일급비밀",
                    "토나오는",
                    "그냥",
                    "매운",
                    "귀여운",
                    "최고의",
                    "기가막힌",
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
                    "배달시킨",
                    "짭짤한",
                    "시큼한",
                    "칠성급",
                    "짜증나는"));

    private static ArrayList<String> food = new ArrayList<String>(
            Arrays.asList("라면",
                    "감자",
                    "만두",
                    "앙버터",
                    "마카롱",
                    "파스타",
                    "피자",
                    "해물찜",
                    "조개탕",
                    "연어회",
                    "연어초밥",
                    "꽃게탕",
                    "간장게장",
                    "김치라면",
                    "김치볶음밥",
                    "김치만두",
                    "쌈밥",
                    "고기만두",
                    "갈비만두",
                    "딸기케이크",
                    "초콜릿",
                    "사탕",
                    "빼빼로",
                    "호두과자",
                    "붕어빵",
                    "떡볶이",
                    "순대",
                    "어묵",
                    "삼겹살"));

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