/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myagent;

import agent.Action;
import agent.Agent;
import agent.Percept;
import vacworld.*;
import java.util.Random;

/* Change the code as appropriate.  This code
   is here to help you understand the mechanism
   of the simulator. 
*/

public class VacAgent extends Agent {

    private final String ID = "1";
    // Think about locations you already visited.  Remember those.
    private boolean dirtStatus = true;
    private boolean bumpFeltInPrevMove = true;
    private boolean obstacleInFront = true;
    private int timesForward = 0;

    public void see(Percept p) {
        System.out.println(p.toString());
        VacPercept vp = (VacPercept) p;
        dirtStatus = vp.seeDirt();
        bumpFeltInPrevMove = vp.feelBump();
        obstacleInFront = vp.seeObstacle();
    }

    public Action selectAction() {
        Action action = new GoForward();
        SuckDirt suckDirt = new SuckDirt();
        TurnLeft turnLeft = new TurnLeft();
        TurnRight turnRight = new TurnRight();
        ShutOff shutOff = new ShutOff();

        System.out.println("dirtStatus");
        System.out.println(dirtStatus);
        System.out.println("bumpFeltInPrevMove");
        System.out.println(bumpFeltInPrevMove);
        System.out.println("obstacleInFront");
        System.out.println(obstacleInFront);

        // make corresponding action
        if(dirtStatus)
            action = suckDirt;
        if(bumpFeltInPrevMove)
              action = turnLeft;
        if(obstacleInFront)
              action = turnRight;
        // in case too many times going forward
        System.out.println(action.getClass().getName());
        if(action.getClass().getName() == "vacworld.GoForward"){
            System.out.println("Test");
            timesForward++;
        }
        // turn left or right
        if(timesForward == 2){
            Random rd = new Random();
            boolean option = rd.nextBoolean();
            if( option == true){
                action = new TurnLeft();
            }
            else {
                action = new TurnRight();
            }
            // reinitialize counter
            timesForward = 0;
        }

        return action;
    }

    public String getId() {
        return ID;
    }

}
