package com.Craffic.myshop.jersey;

import org.junit.Rule;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;

public class BaseRuleMock {
    static {
        PowerMockAgent.initializeIfNeeded();
    }
    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();
}
