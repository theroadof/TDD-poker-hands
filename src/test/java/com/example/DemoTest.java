package com.example;

import com.example.entity.PokerHands;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class DemoTest {
    private static Demo demo;

    @BeforeAll
    public static void setUp() throws Exception {
        demo = new Demo();
    }


    @Test
    void should_return_White_wins_with_high_card_ACE_when_run_given_Black_2H_3D_5S_9C_KD_and_White_2C_3H_4S_8C_AH() {
        //given
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        List<PokerHands> white = asList(new PokerHands(2,"C"),new PokerHands(3,"H"),new PokerHands(4,"S"),
                new PokerHands(8,"C"),new PokerHands(demo.convet("A"),"H"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("White",result);
    }

    @Test
    void should_return_White_wins_with_Straight_when_run_given_Black_2H_3D_5S_9C_KD_and_White_3C_4H_5S_6C_7H() {
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        List<PokerHands> white = asList(new PokerHands(3,"C"),new PokerHands(4,"H"),new PokerHands(5,"S"),
                new PokerHands(6,"C"),new PokerHands(7,"H"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("White",result);
    }

    @Test
    void should_return_White_wins_with_Flush_when_run_given_Black_2H_3D_5S_9C_KD_and_White_2H_3H_5H_9H_KH() {
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        List<PokerHands> white = asList(new PokerHands(2,"H"),new PokerHands(3,"H"),new PokerHands(5,"H"),
                new PokerHands(9,"H"),new PokerHands(demo.convet("K"),"H"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("White",result);
    }

    @Test
    void should_return_Tie_when_run_given_Black_3H_4D_5S_6C_7D_and_White_3C_4H_5S_6C_7H() {
        List<PokerHands> black = asList(new PokerHands(3,"H"),new PokerHands(4,"D"),new PokerHands(5,"S"),
                new PokerHands(6,"C"),new PokerHands(7,"D"));
        List<PokerHands> white = asList(new PokerHands(3,"C"),new PokerHands(4,"H"),new PokerHands(5,"S"),
                new PokerHands(6,"C"),new PokerHands(7,"H"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("Tie.",result);
    }

    @Test
    void should_return_White_wins_with_Straight_Flush_when_run_given_Black_2H_3D_5S_9C_KD_and_White_3H_4H_5H_6H_7H() {
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        List<PokerHands> white = asList(new PokerHands(3,"H"),new PokerHands(4,"H"),new PokerHands(5,"H"),
                new PokerHands(6,"H"),new PokerHands(7,"H"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("White",result);
    }

    @Test
    void should_return_White_wins_with_Pair_when_run_given_Black_2H_3D_5S_9C_KD_and_White_3H_3D_5S_9C_KD() {
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        List<PokerHands> white = asList(new PokerHands(3,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("White",result);
    }

    @Test
        void should_return_White_wins_with_Two_Pairs_when_run_given_Black_2H_3D_5S_9C_KD_and_White_3H_3D_5S_9C_5D() {
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        List<PokerHands> white = asList(new PokerHands(3,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(5,"D"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("White",result);
    }

    @Test
    void should_return_White_wins_with_Three_of_a_Kind_when_run_given_Black_2H_3D_5S_9C_KD_and_White_3H_3D_5S_9C_3D() {
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        List<PokerHands> white = asList(new PokerHands(3,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(3,"D"));

        //when
        String result = demo.run(white,black);

        //then
        assertEquals("White",result);
    }

    @Test
    void test2() {
        List<PokerHands> black = asList(new PokerHands(2,"H"),new PokerHands(3,"D"),new PokerHands(5,"S"),
                new PokerHands(9,"C"),new PokerHands(demo.convet("K"),"D"));
        Map<Integer,List<PokerHands> > map = black.stream().collect(Collectors.groupingBy(PokerHands::getNumber));

        assertFalse(demo.isStraight(map));
    }
}