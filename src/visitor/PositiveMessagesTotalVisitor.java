package visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import group.Group;
import user.User;

public class PositiveMessagesTotalVisitor implements Visitor {
    
    public int total;
    
    private final List<String> positiveWords = Arrays.asList("good", "great", "excellent", "dope", "bodacious", "sublime", "happy", "amazing", "wonderful");

    @Override
    public void atUser(User e) {
        System.out.println("Counted positive messages from User: " + e.getUniqueID());
        countPositiveTweets(e.retrieveTweets());
    } 

    @Override
    public void atGroup(Group e) {
        System.out.println("Counting positive messages of children of Group: " + e.getUniqueID());
    }
    
    private void countPositiveTweets(ArrayList<String> tweets){
        for (String tweet : tweets){
            if (containsPositiveWord(tweet)){
                total++;
            }
        }
    }

    private boolean containsPositiveWord(String tweet){
        String lowercase = tweet.toLowerCase();   
        for (String word : lowercase.split(" ")){
            if (positiveWords.contains(word)){
                return true;
            }
        }  
        return false;
    }    
}
