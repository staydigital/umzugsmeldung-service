package de.nkk.oasis.tools.othworkshop.umzugmeldung.controller;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.process.UmzugMeldenOTHDefinitions;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.process.VertragHandlingOTHDefinitions;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task;

public class UmzugMeldenProcessTest {

    class LoggerDelegate implements JavaDelegate {


        private String delegation;

        private final Logger LOG = LoggerFactory.getLogger(LoggerDelegate.class);

        LoggerDelegate(String delegation) {
            this.delegation = delegation;
        }

        @Override
        public void execute(DelegateExecution delegateExecution) throws Exception {
            LOG.info("LoggerDelegate: {}", this.delegation);
        }
    }

    @Rule
    public ProcessEngineRule rule = new ProcessEngineRule();

    @Before
    public void setup() {
        init(rule.getProcessEngine());
    }

    private JavaDelegate beratungsanlassErstellen = Mockito.mock(JavaDelegate.class);

    @Test
    @Deployment(resources = "process/UmzugMeldenOth.bpmn")
    public void testHappyPathUmzugMelden() {
        Mocks.register("beratungsanlassErstellen", new LoggerDelegate("beratungsanlassErstellen"));
        Mocks.register("vertraegeSuchen", new LoggerDelegate("vertraegeSuchen"));
        ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByKey(UmzugMeldenOTHDefinitions.PROCESSKEY);
        assertThat(pi).isNotNull().isActive().isWaitingAt(UmzugMeldenOTHDefinitions.UMZUGADRESSE_ERFASSEN);

        Map<String, Object> vars = new HashMap<>();
        vars.put("vertraegeIds", Arrays.asList(1L, 2L));

        complete(task(), vars);

        assertThat(pi).hasPassedInOrder(UmzugMeldenOTHDefinitions.STARTEVENT,
                UmzugMeldenOTHDefinitions.UMZUGADRESSE_ERFASSEN,
                UmzugMeldenOTHDefinitions.BERATUNGSANLASS_ERSTELLEN,
                UmzugMeldenOTHDefinitions.VERTRAEGE_SUCHEN,
                UmzugMeldenOTHDefinitions.SUBSTARTEVENT,
                UmzugMeldenOTHDefinitions.SUBENDEVENT,
                UmzugMeldenOTHDefinitions.SUBSTARTEVENT,
                UmzugMeldenOTHDefinitions.SUBENDEVENT,
                UmzugMeldenOTHDefinitions.ENDEVENT);
        assertThat(pi).isEnded();
    }

    @Test
    @Deployment(resources = "process/VertragHandlingOth.bpmn")
    public void testHappyPathVertagHandling() {
        Mocks.register("vertragLesen", new LoggerDelegate("vertragLesen"));
        ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByKey(VertragHandlingOTHDefinitions.PROCESSKEY);
        assertThat(pi).isEnded();
    }


}
