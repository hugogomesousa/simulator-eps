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
 *
 * @author hugo
 */
public class ConveyorDestiny extends MRA{
    
    // Destinos dos produtos: Conveyor Green, Conveyor Blue ou Conveyor Red
    private static final int GREEN = 0;
    private static final int BLUE = 1;
    private static final int RED = 2;
    
     /**
     * Meodo setup() da classe. Seta a propriedade da skill "moveto" e adiciona
     * as skills ao vetor de skills.
     */
    @Override
    protected void setup(){
        this.skills = new Skill[]{move_to};
        defaultSetup();
        addResponderBehaviour();
    }
    
     /**
     * Implementa uma skill chamada "moveto", que será externalizada como 
     * serviço através do YPA e é capaz de mover os produtos. 

     */
    
    protected Skill move_to = new Skill(this, "moveto", "void", new String[0]) {
        @Override
        public void execute() throws SkillExecuteException {
           // int direction = Integer.parseInt(getArgsValues()[0]);
           // switch(direction){
             //   case GREEN:
                    System.out.println(this.myMRA.getLocalName() + " is moving");
               //     break;
               // case BLUE:
                 //   System.out.println(this.myMRA.getLocalName() + " is moving");
                   // break;
                //case RED:
                  //  System.out.println(this.myMRA.getLocalName() + " is moving");
                  //  break;
               // default:
                 //   System.out.println("Conveyor is stopped");
                  //  break;
            }
        };
            
   // };       

    @Override
    protected MRAInfo getMRAInfo() {
        myMrainfo = new MRAInfo();
        myMrainfo.setAID(this.getLocalName());
        myMrainfo.setSkills(Util.fromSkill(getSkills()));
        return myMrainfo;
    }

    @Override
    protected Skill[] getSkills() {
        return this.skills;
    }
    
}
