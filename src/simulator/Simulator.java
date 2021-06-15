/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import eps.MRA;
import eps.MRAInfo;
import eps.Skill;
import eps.Util;
import eps.YPA;
import eps.ontology.EPSOntology;

import jade.content.lang.sl.SLCodec;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import org.jdom2.input.SAXBuilder;

/**
 *
 * @author hugo
 */
public class Simulator extends Agent {

    private String filename;
    private Runtime runtime;
    private Profile p;
    private ContainerController cc;
    private AgentController ac;
    private MRAInfo mraInfo;
    
    

    @Override
    protected void setup() {
        createContainer();
        init();
        super.setup();
        defaultSetup();
        readXML();
        instanciaYPA();

    }
    
    
    /**
     * método responsável por criar um contêiner periférico JADE, onde
     * os Agentes cyber-físicos estão alocados. 
     */
    protected void createContainer() {
        runtime = Runtime.instance(); // tempo de execução JADE 
        p = new ProfileImpl(false);
        cc = runtime.createAgentContainer(p);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * método para inicialização do XML, pega o argumento que é definido pelo
     * caminho do arquivo xml incluído na classe Main.
     */
    protected void init() {
        this.filename = getArguments()[0].toString();
    }

    /**
     * método para a leitura do XML através da API JDOM.
     *
     */

    protected void readXML() {
        
        Document doc = null;
        System.out.println("Lendo o xml");
        SAXBuilder builder = new SAXBuilder();
        try {
            URL url = new File(filename).toURI().toURL();
            doc = builder.build(url);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        Element fsm = doc.getRootElement();

        for (Element e : fsm.getChildren()) {
            if (e.getName().equals("Simulate")) {
                for (Element e1 : e.getChildren()) {
                    if (e1.getName().equals("MA")) {
                        int timestamp = Integer.parseInt(e1.getAttributeValue("timestamp"));
                        String nameAg = e1.getAttributeValue("name");
                        String classAg = e1.getAttributeValue("class");
                        List<String> argsValues = new LinkedList<>(); // criar uma lista do tipo String
                        for (Element e2 : e1.getChildren()) { // vai percorrer o elemento filho
                            if (e2.getName().equals("Arg")) { //se for igual a Arg
                                argsValues.add(e2.getText());// vai adicionar na lista o texto presente em Arg
                            }
                        }
                        instanciaMA(nameAg, classAg, argsValues.toArray(new String[0]), this, timestamp);

                    } 

                }
            }

        }
       
        
    }

    /**
     * Método para instanciar os agentes mecatrônicos descritos no XML
     *
     * @param name - nome do agente que está especificado no XML
     * @param className - classe do agente que está especificado no XML
     * @param args - Uma matriz de objeto, contendo parâmetros de inicialização
     * descritos no XML para passar para o novo agente.
     * @param a - indica qual agente que executará este comportamento
     * @param timeout - o agente executára seu comportamento após esse tempo
     * definido através do xml *
     * @return 
     *
     */
    protected void instanciaMA(String name, String className, String[] args, Agent a, long timeout) {        
        WakerBehaviour wb = new WakerBehaviour(this, timeout) {
            @Override
            protected void onWake() {
                try {
                    
                    System.out.println("Instantiating the agent: " + name);
                    //  AgentContainer cc = myAgent.getContainerController();
                    AgentController ac = cc.createNewAgent(name, className, args);
                    ac.start();
                } catch (StaleProxyException ex) {
                    Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        addBehaviour(wb);
    }

    /**
     * Método para instanciar o agente de páginas amarelas.
     */
    protected void instanciaYPA() {
        try {
            YPA ypa = new YPA();
            ac = cc.acceptNewAgent(YPA.YPA_AGENT_NAME, ypa);
            ac.start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void defaultSetup() {
        getContentManager().registerLanguage(new SLCodec());
        getContentManager().registerOntology(EPSOntology.instance());
    }
    


}
