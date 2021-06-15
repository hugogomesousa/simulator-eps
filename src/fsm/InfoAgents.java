/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;


import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import java.util.Iterator;

/**
 *
 * @author hugo
 */
public class InfoAgents extends Agent {

    protected void setup() {
        System.out.println("Hello World. I'm an agent! " + getAID().getLocalName() + 
                " e executarei minha máquina de Estados");
        fsmBehaviour();
    }

    public void fsmBehaviour() {
        FSMBehaviour fsm = new FSMBehaviour(this);
        fsm.registerFirstState(new OneShotBehaviour(this) {
            int c = 0;

            @Override
            public void action() {
                System.out.println(myAgent.getAID().getLocalName()
                        + " está Executando o comportamento X");
                c++;
            }

            public int onEnd() {
                return (c > 3 ? 1 : 0);
            }
        }, "X");
        fsm.registerLastState(new OneShotBehaviour(this) {
            @Override
            public void action() {
                System.out.println(myAgent.getAID().getLocalName()
                        + " está executando seu último comportamento");
            }
        }, "Y");
        fsm.registerState(new OneShotBehaviour(this) {
            @Override
            public void action() {
                System.out.println(myAgent.getAID().getLocalName()
                        + " está comportamento Z");
            }
        }, "Z");
        fsm.registerTransition("X", "Y", 1);
        fsm.registerTransition("X", "Z", 0);
        fsm.registerDefaultTransition("Z", "X", new String[]{"X", "Z"});
        addBehaviour(fsm);
    }

}


/* O nome local do Agente é Hugo;
O nome global (GUID) - GLobally Unique Name do agente é Hugo@192.168.0.26:1099/JADE
O endereço do agente é: http://hugo-Inspiron-15-3567:7778/acc
-name desgina o nome da plataforma 
 */
