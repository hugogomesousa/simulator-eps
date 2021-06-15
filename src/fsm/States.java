/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;

/**
 *
 * @author hugo
 */
public class States {
    private String state;

    public States(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
    public String toString(){
        return "state = " + state;
    }
    
}
