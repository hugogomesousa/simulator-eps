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
public class Transitions {

    private String from;
    private String to;
    private String event;

    public Transitions(String from, String to, String event) {
        if (!event.matches("[\\*\\d]")) {
            throw new NumberFormatException(event + " found but integer or * expected");
        }
        this.from = from;
        this.to = to;
        this.event = event;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Transition{" + "from=" + from + ", to=" + to + ", event=" + event + '}';
    }

}
