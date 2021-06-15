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
public class AgentsDescription {
    private String name;
    private String skill;

    public AgentsDescription(String name, String skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
   
    public String toString(){
        return "Agent = " + name + " - " + " skill = " + skill;
    }
    
}
