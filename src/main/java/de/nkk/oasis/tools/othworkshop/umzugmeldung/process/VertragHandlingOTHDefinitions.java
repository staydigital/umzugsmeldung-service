package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.util.List;

public class VertragHandlingOTHDefinitions {

    public static final String PROCESSKEY = "VertragHandlingOth";

    public final static String STARTEVENT = "StartEvent";
    public final static String VERTRAG_LESEN = "Srv_VertragLesen";
    public static final String AENDERUNGSMITTEILUNG = "Srv_Aenderungsmitteilung";
    public final static String ENDEVENT = "EndEvent";

    public static Long getVertragId(DelegateExecution execution) {
        return (Long) execution.getVariable("vertragId");
    }

    public static Long getAdresseId(DelegateExecution execution) {
        return (Long) execution.getVariable("adresseId");
    }

    public static Long getVnId(DelegateExecution execution) {
        return (Long) execution.getVariable("vnId");
    }

    public static void setRisikoGefunden(DelegateExecution execution, Boolean risikoGefunden) {
        execution.setVariable("risikoGefunden", risikoGefunden);
    }

    public static void setSparte(DelegateExecution execution, String sparte) {
        execution.setVariable("sparte", sparte);
    }

}
