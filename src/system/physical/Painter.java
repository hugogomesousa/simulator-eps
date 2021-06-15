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
 * Classe responsável por definir o agente mecatrônico (Painter) que é
 * responsável por pintar os produtos de acordo com uma cor (Verde, azul ou
 * Vermelho) definida através de uma ordem de produção
 *
 * @author hugo
 */
public class Painter extends MRA {
    
    /**
     * Método setup() da classe. Seta a propriedade da skill "move" e adiciona a
     * skills ao vetor de skills.
     */
    @Override
    protected void setup(){
        skills = new Skill[]{pinter};
        defaultSetup();
        addResponderBehaviour();
    }
    

    /**
     * Implementa uma skill "pinter" que será disponibilizada como serviço
     * através do YPA e é capaz de atribuir uma pintura (verde, azul ou
     * vermelho) ao produto.
     */
    protected Skill pinter = new Skill(this, "pinter", "void", new String[0]) {
        @Override
        public void execute() throws SkillExecuteException {
            System.out.println(this.myMRA.getLocalName() + ": painting");
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
