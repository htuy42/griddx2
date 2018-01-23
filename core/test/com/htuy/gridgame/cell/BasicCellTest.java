package com.htuy.gridgame.cell;

import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BasicCellTest {

    private Random random;

    @Before
    public void setup(){
        random = new Random();
    }

    @Test
    public void getHeight() throws Exception {
        for(int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++){
            Cell c = new BasicCell(i);
            assert(c.getHeight() == i);
        }
    }

    @Test
    public void toStringTest() throws Exception {
        for(int i = 0; i < 10000000; i++){
            //Using the whole range takes too long with strings. So we use random elements pulled from the range, many times.
            int height = random.nextInt();
            Cell c = new BasicCell(height);
            assert(c.toString().equals(String.valueOf(height)));
        }
    }

}