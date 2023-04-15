package com.example.myfirstapp.animantions;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animantion {
    private TranslateTransition translateTransition;

    public void shake(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(50), node) ;
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);

        translateTransition.playFromStart();
    }

    public void fade(Node node, Boolean autoReverse, float fromValue, float toValue, int cycleCount, int time){
        FadeTransition fadeTransitionBtn = new FadeTransition(Duration.millis(time),node);
        //Remove the Items by FadeTransition functions
        fadeTransitionBtn.setFromValue(fromValue);
        fadeTransitionBtn.setToValue(toValue);
        fadeTransitionBtn.setCycleCount(cycleCount);
        fadeTransitionBtn.setAutoReverse(autoReverse);
        fadeTransitionBtn.play();
    }
}
