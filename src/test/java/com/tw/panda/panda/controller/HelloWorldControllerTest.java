package com.tw.panda.panda.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloWorldControllerTest {

    @Test
    public void test() {
        HelloWorldController helloWorldController = new HelloWorldController();
        String actualString = helloWorldController.hello();

        assertThat(actualString).isEqualTo("Hello World!!!");
    }
}
