package main;

import eps.YPA;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulator.Simulator;

/**
 *
 * @author hugo
 */
public class Main {

    public static void main(String[] args) throws StaleProxyException, InterruptedException {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl(true);
        ContainerController mainContainer = rt.createMainContainer(p);
        AgentController ac;
        ac = mainContainer.createNewAgent("Simulator", "simulator.Simulator", new Object[]{"/home/hugo/NetBeansProjects/JavaApplication13/src/xml/teste02.xml"});
        AgentController rma;
        rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", args);
        ac.start();
        rma.start();

    }
}
