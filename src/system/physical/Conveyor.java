/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.physical;

import eps.MRA;
import eps.MRAInfo;
import eps.Skill;
import eps.SkillExecuteException;
import eps.Util;

/**
 * Classe responsável por definir o agente mecatrônico (Conveyor) que é
 * responsável pelo transporte dos produtos até o ponto de identificação.
 *
 * @author hugo
 */
public class Conveyor extends MRA {
    
    /**
     * Método setup() da classe. Seta a propriedade da skill "move" e adiciona
     * a skills ao vetor de skills. 
     */
    
    @Override
    protected void setup(){
        skills = new Skill[]{move};
        defaultSetup(); 
        addResponderBehaviour();
    }
    
    /**
     * Implementa uma skill denominada "move", que será disponibilizada como serviço através
     * do YPA e é capaz de mover os produtos até o ponto de identificação.
     */
    protected Skill move = new Skill(this, "move", "void", new String[0]) {
        @Override
        public void execute() throws SkillExecuteException {
            isBusy = true;
            System.out.println(this.myMRA.getLocalName()+ ": is moving");
            isBusy = false;  
        }
           
    };


    protected MRAInfo getMRAInfo() {
        myMrainfo = new MRAInfo();
        myMrainfo.setAID(this.getLocalName()); //seta o nome local do agente, no caso "Conveyor"
        myMrainfo.setSkills(Util.fromSkill(getSkills()));//seta a skill
        return myMrainfo;
    }
    
    
    /**
     * Este método para retornar um array com as skills deste MRA, no caso a skill "move".
     */

    @Override
    protected Skill[] getSkills() {
        return skills;
    }

}
