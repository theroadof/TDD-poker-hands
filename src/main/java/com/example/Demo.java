package com.example;

import com.example.entity.PokerHands;
import com.example.entity.PokerType;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {

    public static final String FULL_HOUSE = "Full House";
    public static final String FOUR_OF_A_KIND = "Four of a Kind";
    public static final String TWO_PAIRS = "Two Pairs";
    public static final String THREE_OF_A_KIND = "Three of a Kind";
    public static final String PAIR = "Pair";
    public static final String HIGH_CARD = "High Card";
    public static final String FLUSH = "Flush";
    public static final String STRAIGHT = "Straight";
    public static final String STRAIGHT_FLUSH = "Straight Flush";
    public static final String TIE = "Tie";

    public String run(List<PokerHands> white, List<PokerHands> black) {
        PokerType whitePokerType = judgePokerType(white);
        whitePokerType.setWinner("White");
        PokerType blackPokerType = judgePokerType(black);
        blackPokerType.setWinner("Black");
        PokerType winnerPoker = compare(whitePokerType, blackPokerType);
        if (winnerPoker.getWinner().equals(TIE)) {
            return "Tie.";
        }
        return winnerPoker.getWinner()+" wins. - with "+winnerPoker.getType();
    }

    public PokerType compare(PokerType firstPokerType, PokerType secondPokerType) {
        firstPokerType.setLevel(calculateLevel(firstPokerType));
        secondPokerType.setLevel(calculateLevel(secondPokerType));
        if (firstPokerType.getLevel() == secondPokerType.getLevel()) {
            if (firstPokerType.getMaxNumber() > secondPokerType.getMaxNumber()) {
                return firstPokerType;
            } else if (firstPokerType.getMaxNumber() < secondPokerType.getMaxNumber()) {
                return secondPokerType;
            } else {
                for(int i=firstPokerType.getNumbers().size()-2;i>=0;i--){
                    if(firstPokerType.getNumbers().get(i)>secondPokerType.getNumbers().get(i)){
                        return firstPokerType;
                    }else if(firstPokerType.getNumbers().get(i)<secondPokerType.getNumbers().get(i)){
                        return secondPokerType;
                    }
                }
                PokerType poker = new PokerType();
                poker.setWinner(TIE);
                return poker;
            }
        } else {
            if (firstPokerType.getLevel() > secondPokerType.getLevel()) {
                return firstPokerType;
            }
            return secondPokerType;
        }
    }

    private int calculateLevel(PokerType pokerType) {
        int level = 0;
        switch (pokerType.getType()) {
            case HIGH_CARD:
                level = 1;
                break;
            case PAIR:
                level = 2;
                break;
            case TWO_PAIRS:
                level = 3;
                break;
            case THREE_OF_A_KIND:
                level = 4;
                break;
            case STRAIGHT:
                level = 5;
                break;
            case FLUSH:
                level = 6;
                break;
            case FULL_HOUSE:
                level = 7;
                break;
            case FOUR_OF_A_KIND:
                level = 8;
                break;
            case STRAIGHT_FLUSH:
                level = 9;
                break;
            default:
                break;
        }
        return level;
    }

    public PokerType judgePokerType(List<PokerHands> pokerHandsList) {
        Map<Integer, List<PokerHands>> numberMap = pokerHandsList.stream().collect(Collectors.groupingBy(PokerHands::getNumber));
        Map<String, List<PokerHands>> colorMap = pokerHandsList.stream().collect(Collectors.groupingBy(PokerHands::getPokerColor));
        PokerType pokerType = new PokerType();
        List<Integer> mapKey = new ArrayList<>(numberMap.keySet());
        Collections.sort(mapKey);
        if (numberMap.size() == 5) {
            if (isStraight(numberMap) && colorMap.size() == 1) {
                pokerType.setType(STRAIGHT_FLUSH);
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            } else if (isStraight(numberMap) && colorMap.size() != 1) {
                pokerType.setType(STRAIGHT);
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            } else if (!isStraight(numberMap) && colorMap.size() == 1) {
                pokerType.setType(FLUSH);
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            } else {
                pokerType.setType(HIGH_CARD);
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            }
            pokerType.setNumbers(mapKey);
            return pokerType;
        } else if (numberMap.size() == 4) {
            pokerType.setType(PAIR);
            for (int index : mapKey) {
                if (numberMap.get(index).size() == 2) {
                    pokerType.setMaxNumber(index);
                    break;
                }
            }
            pokerType.setNumbers(mapKey);
            return pokerType;
        } else if (numberMap.size() == 3) {
            for (int index : mapKey) {
                if (numberMap.get(index).size() == 3) {
                    pokerType.setType(THREE_OF_A_KIND);
                    pokerType.setMaxNumber(index);
                    break;
                }
            }
            if (Objects.isNull(pokerType.getType())) {
                pokerType.setType(TWO_PAIRS);
                for (int i = mapKey.size() - 1; i >= 0; i--) {
                    if (numberMap.get(mapKey.get(i)).size() == 2) {
                        pokerType.setMaxNumber(mapKey.get(i));
                        break;
                    }
                }
            }
            pokerType.setNumbers(mapKey);
            return pokerType;
        } else {
            for (int index : mapKey) {
                if (numberMap.get(index).size() == 4) {
                    pokerType.setType(FOUR_OF_A_KIND);
                    pokerType.setMaxNumber(index);
                    break;
                }
                if (numberMap.get(index).size() == 3) {
                    pokerType.setType(FULL_HOUSE);
                    pokerType.setMaxNumber(index);
                    break;
                }
            }
            pokerType.setNumbers(mapKey);
            return pokerType;
        }
    }

    public boolean isStraight(Map<Integer, List<PokerHands>> map) {
        boolean result = true;
        Set<Integer> set = map.keySet();
        Iterator<Integer> iterator = set.iterator();
        int num = iterator.next();
        while (iterator.hasNext()) {
            int nextNum = iterator.next();
            if (nextNum != num + 1) {
                result = false;
                break;
            }
            num = nextNum;
        }
        return result;
    }

    public int convet(String input) {
        int result;
        switch (input) {
            case "J":
                result = 11;
                break;
            case "Q":
                result = 12;
                break;
            case "K":
                result = 13;
                break;
            case "A":
                result = 14;
                break;
            default:
                result = Integer.parseInt(input);
                break;
        }
        return result;
    }
}
