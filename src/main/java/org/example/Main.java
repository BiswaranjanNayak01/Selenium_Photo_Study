package org.example;

import org.testng.annotations.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite");
    }
    @AfterTest
    public void AfterTest(){
        System.out.println("AfterTest");
    }
    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass");
    }
    @AfterGroups
    public void AfterGroups(){
        System.out.println("AfterGroups");
    }
    @AfterMethod
    public void AfterMethod(){
        System.out.println("AfterMethod");
    }
}