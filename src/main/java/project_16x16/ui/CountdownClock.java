package project_16x16.ui;

public class CountdownClock {
    private long startTime, timeLimit, elapsedSec;
    private boolean paused;

    public CountdownClock(long timeLimit){
        startTime = System.currentTimeMillis();
        this.timeLimit = timeLimit;
        paused = false;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public boolean isPaused() {
        return paused;
    }

    public long timeRemaining(){
        long timeLeft;
        if(!paused){
            timeLeft = timeLimit - this.calculateTime();
        }
        else{
            timeLeft = timeLimit;
        }
        return timeLeft;
    }

    public long calculateTime(){ //calculates time elapsed in seconds
        elapsedSec = (System.currentTimeMillis() - startTime)/1000;
        return elapsedSec;
    }
    public void pause(){
        timeLimit = timeRemaining();
        paused = true;
    }
    public void unpause(){
        startTime = System.currentTimeMillis();
        paused = false;
    }

    public static void main(String args[]){
        long secondsPaused = 0;
        System.out.println("TEST: 10 sec countdown");
        CountdownClock c = new CountdownClock(10);
        while(c.timeRemaining() >= 0){
            if(c.isPaused()){
                secondsPaused += 1;
            }
            System.out.println(c.timeRemaining());
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
    }
}
