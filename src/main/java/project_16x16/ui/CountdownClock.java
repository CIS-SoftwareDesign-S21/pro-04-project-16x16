package project_16x16.ui;

public class CountdownClock {
    private long startTime, timeLimit, elapsedSec;
    private boolean paused;

    public CountdownClock(long timeLimit){
        startTime = System.currentTimeMillis();
        if(timeLimit > 0) {
            this.timeLimit = timeLimit;
        }
        else{
            this.timeLimit = 0;
        }
        elapsedSec = 0;
        paused = false;
    }

    public boolean hasTimeLimit() {
        return (timeLimit > 0);
    }

    public boolean isPaused() {
        return paused;
    }

    public long timeRemaining(){//subtracts time elapsed from timeLimit
        long timeLeft;
        timeLeft = timeLimit - this.calculateTime();
        return timeLeft;
    }

    public long calculateTime(){ //calculates time elapsed in seconds
        if(!paused) {
            elapsedSec = (System.currentTimeMillis() - startTime) / 1000;
        }
        return elapsedSec;
    }
    public void pause(){
        elapsedSec = calculateTime();
        paused = true;
    }
    public void unpause(){
        timeLimit = timeRemaining();
        startTime = System.currentTimeMillis();
        paused = false;
    }
    public String timeToString(){ //fetches time remaining, or time elapsed, then converts it to formatted string
        long displayTime;
        if (hasTimeLimit()){
            displayTime = timeRemaining();
        }
        else{
            displayTime = calculateTime();
        }
        return formatString(displayTime);
    }
    private String formatString(long displayTime){ //formats time string in form 0:00
        String tStr = "";
        if (displayTime >= 60) {
            tStr += (displayTime / 60) + ":";
        }
        else{
            tStr += "0:";
        }
        if((displayTime % 60) < 10){
            tStr += "0";
        }
        tStr += (displayTime % 60);
        return tStr;
    }

    public static void main(String args[]){
        long secondsPaused = 0;
        System.out.println("TEST: 70 sec countdown");
        CountdownClock c = new CountdownClock(70);
        while(c.timeRemaining() >= 0){
            if(c.isPaused()){
                secondsPaused += 1;
            }
            System.out.println(c.timeToString());
            if(c.timeRemaining() == 5 && !c.isPaused()){
                System.out.println("TEST: pause countdown");
                c.pause();
            }
            if (secondsPaused >= 3 && c.isPaused()){
                System.out.println("TEST: resume countdown");
                c.unpause();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("TEST: 30 sec count up");
        c = new CountdownClock(0);
        for(int i = 0; i <= 30; i++){
            System.out.println(c.timeToString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}