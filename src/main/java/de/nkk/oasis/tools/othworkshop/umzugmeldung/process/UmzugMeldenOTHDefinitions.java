package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.*;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UmzugMeldenOTHDefinitions {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired private VNRepository vnRepository;

    @Autowired private BeratungsprotokollRepository beratungsprotokollRepository;


    public static final String PROCESSKEY = "UmzugMeldenOth";
    public static final String STARTEVENT = "StartEvent";
    public static final String SUBSTARTEVENT = "SubStartEvent";

    public static final String ENDEVENT = "EndEvent";
    public static final String SUBENDEVENT = "SubEndEvent";

    public static final String UMZUGADRESSE_ERFASSEN = "UserTask_UmzugsadresseErfassen";

    public static final String BERATUNGSANLASS_ERSTELLEN = "Srv_BeratungsanlassErstellen";

    public static final String VERTRAEGE_SUCHEN = "Srv_VertraegeSuchen";

    public static Long getVnId(DelegateExecution delegateExecution) {
        return (Long) delegateExecution.getVariable("vnId");
    }

    public static void setVertraegeIds(DelegateExecution execution, List<Long> vertraegIds) {
        execution.setVariable("vertraegeIds", vertraegIds);
    }

    public void startUmzugMeldenProcess(Umzugsmeldung umzugsmeldung) {
        VN vn = vnRepository.findOne(umzugsmeldung.getVnId());

        Map<String, Object> vars = new HashMap<>();
        vars.put("vnId", umzugsmeldung.getVnId());
        vars.put("adresseId", umzugsmeldung.getAdresseId());

        runtimeService.startProcessInstanceByKey(PROCESSKEY, vn.getKundennummer(), vars);
    }

    public void completeTask(Vertrag vertrag) {
        Task task = this.taskService.createTaskQuery().processInstanceId(vertrag.getActivityId()).singleResult();
        taskService.complete(task.getId());

        beratungsprotokollRepository.deleteByVertragId(vertrag.getId());
    }

    public void mockUmzug() {

        List<VN> vns = vnRepository.findAll();

        List<Adresse> adressen = adresseRepository.findAll();
        for (int i = 0; i <= 10; i++) {
            for (VN vn : vns) {
                for (Adresse adresse : adressen) {
                    Map<String, Object> vars = new HashMap<>();
                    vars.put("vnId", vn.getId());
                    vars.put("adresseId", adresse.getId());

                    runtimeService.startProcessInstanceByKey(PROCESSKEY, vn.getKundennummer(), vars);
                }
            }
        }

        List<Task> tasks = this.taskService.createTaskQuery().processDefinitionKey(VertragHandlingOTHDefinitions.PROCESSKEY).active().list();
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }

        List<Beratungsprotokoll> protokolle = beratungsprotokollRepository.findAll();
        for (Beratungsprotokoll protokoll : protokolle) {
            beratungsprotokollRepository.delete(protokoll);
        }
    }
}
