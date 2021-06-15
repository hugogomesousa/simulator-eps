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
 * Classe responsável por definir o agente mecatrônico (ColorPicker) que é
 * responsável por definir o destino dos produtos (Verde, azul ou Vermelho) para uma
 * esteira de destino
 *
 * @author hugo
 */
public class ColorPicker extends MRA {

    
    @Override
    protected void setup(){
        this.skills = new Skill[]{separate};
        defaultSetup();
        addResponderBehaviour();
    }

    
    /**
     * Implementa uma skill "separate" que será disponibilizada como serviço
     * através do YPA e é capaz de definir o destino do produto para uma esteira
     * de acordo com a cor estabelecida.
     */
    protected Skill separate = new Skill(this, "separate", "void", new String[0]) {
        @Override
        public void execute() throws SkillExecuteException {
            System.out.println(this.myMRA.getLocalName() + " : Selecting");
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
