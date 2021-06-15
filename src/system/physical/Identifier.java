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
 * Classe responsável por definir o agente mecatrônico (Identificador) que é
 * responsável por definir se há presença de um produto ou não.
 *
 * @author hugo
 */
public class Identifier extends MRA {

    private static final int OUT = 0; //não há produtos
    private static final int IN = 1; //há produtos

    /**
     * Método setup() da classe. Seta a propriedade da skill "move" e adiciona a
     * skills ao vetor de skills.
     */
    @Override
    protected void setup() {
        skills = new Skill[]{identify};
        defaultSetup();
        addResponderBehaviour();

    }

    /**
     * Implementa uma skill denomidada "identify" que será disponibilizada como
     * serviço através do YPA e é capaz de identificar se há produtos ou não. É
     * equivalente a um sensor de presença.
     */
    protected Skill identify = new Skill(this, "identify", "void", new String[0]) {
        @Override
        public void execute() throws SkillExecuteException {
            isBusy = true;
          //  int presence = Integer.parseInt(getArgsValues()[0]);
          //  switch (presence) {
            //    case OUT:
                    System.out.println(this.myMRA.getLocalName() + ": did not identify presence of product");
              //      break;
              //  case IN:
                //    System.out.println(this.myMRA.getLocalName() + ": identified product presence");
                  //  break;
                ///default:
                   // System.out.println("Identify is stopped");
                   // break;
            //}
            //isBusy = false;
        }
    };

    @Override
    protected MRAInfo getMRAInfo() {
        myMrainfo = new MRAInfo();
        myMrainfo.setAID(this.getLocalName());
        myMrainfo.setSkills(Util.fromSkill(getSkills()));
        return myMrainfo;

    }

    @Override
    protected Skill[] getSkills() {
        return skills;
    }

}
