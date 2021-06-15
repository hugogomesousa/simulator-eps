/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.cognitive;

import eps.MRAInfo;
import eps.Product;
import eps.Skill;
import eps.SkillTemplate;
import eps.Util;
import eps.YPAException;
import eps.YPAServices;
import eps.ontology.EPSOntology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.ArrayList;
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
public class Production extends Product {

    private String product;
    private Plan myPlan;

    protected void setup() {

        this.myPlan = new Plan(this);
        defaultSetup();
        produce();

    }

    @Override
    protected void produce() {
        try {
            readXML();
        } catch (YPAException ex) {
            Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
        }
        executePlan();

    }

    protected void readXML() throws YPAException {
        Document doc = null;
        SAXBuilder builder = new SAXBuilder();
        try {
            doc = builder.build("/home/hugo/NetBeansProjects/JavaApplication13/src/xml/teste03.xml");
        } catch (JDOMException ex) {
            Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
        }
        Element simulator = doc.getRootElement();

        for (Element e : simulator.getChildren()) {
            if (e.getName().equals("SkillCall")) {
                String name = e.getAttributeValue("name");
                String resultType = e.getAttributeValue("resultType");
                List<String> argsTypes = new ArrayList<>();
                for (Element e1 : e.getChildren()) {
                    if (e1.getName().equals("Arg")) {
                        argsTypes.add(e1.getText());
                    }

                }

                createPlan(name, resultType, argsTypes.toArray(new String[0]));
            }

        }

    }

    private void createPlan(String nameSkill, String resultType, String[] argsTypes) throws YPAException {

        SkillTemplate st = new SkillTemplate(nameSkill, resultType, argsTypes);
        st.setArgsValues(argsTypes);
        MRAInfo[] mraInfo = YPAServices.search(this, st);
        myPlan.addNewPlanItem(mraInfo, st);

    }

    private void executePlan() {
        myPlan.execute();
    }

    protected MRAInfo getMRAInfo() {
        myMrainfo = new MRAInfo();
        myMrainfo.setAID(this.getLocalName());
        myMrainfo.setSkills(Util.fromSkill(getSkills()));
        return myMrainfo;
    }

    @Override
    protected void takeDown() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("Simulator", AID.ISLOCALNAME));
        msg.setContent("Produção");
        send(msg);
        System.out.println(getLocalName() + " : Finalizou a produção");
    }

}
