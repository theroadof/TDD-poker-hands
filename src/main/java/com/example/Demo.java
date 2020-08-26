package com.example;

import com.example.entity.PokerHands;
import com.example.entity.PokerType;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {

    public String run(List<PokerHands> white, List<PokerHands> black) {
        PokerType whitePokerType = judgePokerType(white);
        whitePokerType.setWinner("White");
        PokerType blackPokerType = judgePokerType(black);
        blackPokerType.setWinner("Black");
        PokerType winnerPoker = compare(whitePokerType, blackPokerType);
        if (winnerPoker.getWinner().equals("Tie")) {
            return "Tie.";
        }
        return winnerPoker.getWinner();
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
                PokerType poker = new PokerType();
                poker.setWinner("Tie");
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
            case "High Card":
                level = 1;
                break;
            case "Pair":
                level = 2;
                break;
            case "Two Pairs":
                level = 3;
                break;
            case "Three of a Kind":
                level = 4;
                break;
            case "Straight":
                level = 5;
                break;
            case "Flush":
                level = 6;
                break;
            case "Full House":
                level = 7;
                break;
            case "Four of a Kind":
                level = 8;
                break;
            case "Straight Flush":
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
                pokerType.setType("Straight Flush");
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            } else if (isStraight(numberMap) && colorMap.size() != 1) {
                pokerType.setType("Straight");
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            } else if (!isStraight(numberMap) && colorMap.size() == 1) {
                pokerType.setType("Flush");
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            } else {
                pokerType.setType("High Card");
                pokerType.setMaxNumber(mapKey.get(mapKey.size() - 1));
            }
            return pokerType;
        } else if (numberMap.size() == 4) {
            pokerType.setType("Pair");
            for (int index : mapKey) {
                if (numberMap.get(index).size() == 2) {
                    pokerType.setMaxNumber(index);
                    break;
                }
            }
            return pokerType;
        } else if (numberMap.size() == 3) {
            for (int index : mapKey) {
                if (numberMap.get(index).size() == 3) {
                    pokerType.setType("Three of a Kind");
                    pokerType.setMaxNumber(index);
                    break;
                }
            }
            if (Objects.isNull(pokerType.getType())) {
                pokerType.setType("Two Pairs");
                Collections.sort(mapKey);
                for (int i = mapKey.size() - 1; i >= 0; i--) {
                    if (numberMap.get(mapKey.get(i)).size() == 2) {
                        pokerType.setMaxNumber(mapKey.get(i));
                        break;
                    }
                }
            }
            return pokerType;
        } else {
            for (int index : mapKey) {
                if (numberMap.get(index).size() == 4) {
                    pokerType.setType("Four of a Kind");
                    pokerType.setMaxNumber(index);
                    break;
                }
                if (numberMap.get(index).size() == 3) {
                    pokerType.setType("Full House");
                    pokerType.setMaxNumber(index);
                    break;
                }
            }
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
                result = Integer.valueOf(input);
                break;
        }
        return result;
    }
}
