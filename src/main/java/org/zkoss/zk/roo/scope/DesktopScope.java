package org.zkoss.zk.roo.scope;

import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.ObjectFactory;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

public class DesktopScope implements Scope {

    private static final String PREFIX = "$D$";

    private Desktop getDesktop$SafeCall() {
        Execution exe = Executions.getCurrent();
        if(exe == null) return null;

        Desktop desktop = exe.getDesktop();
        if(desktop == null) return null;
        return desktop;
    }

    public Object get(String name, ObjectFactory<?> objectFactory) {
        Desktop desktop = getDesktop$SafeCall();
        if(desktop == null) return null;
        Object obj = desktop.getAttribute(PREFIX + name);
        if(obj == null) {
            obj = objectFactory.getObject();
            desktop.setAttribute(PREFIX + name, obj);
        }
        return obj;
    }

    public Object remove(String name) {
        Desktop desktop = getDesktop$SafeCall();
        if(desktop == null) return null;

        return desktop.removeAttribute(PREFIX + name);
    }

    public void registerDestructionCallback(String name, Runnable callback) {
    }

    public Object resolveContextualObject(String key) {
        return null;
    }

    public String getConversationId() {
        Desktop desktop = getDesktop$SafeCall();
        if(desktop == null) return "";

        return desktop.getId();
    }

}