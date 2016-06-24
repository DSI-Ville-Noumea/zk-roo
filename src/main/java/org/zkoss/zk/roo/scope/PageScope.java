package org.zkoss.zk.roo.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.ExecutionCtrl;

public class PageScope implements Scope {

    private static final String PREFIX = "$P$";

    private Page getPage$SafeCall() {
        Execution exe = Executions.getCurrent();
        if(exe == null) return null;

        if(exe instanceof ExecutionCtrl == false) return null;
        ExecutionCtrl exeCtrl = (ExecutionCtrl)exe;

        Page page = exeCtrl.getCurrentPage();
        if(page == null) return null;

        return page;
    }

    public Object get(String name, ObjectFactory<?> objectFactory) {
        Page page = getPage$SafeCall();
        if(page == null) return null;
        Object obj = page.getAttribute(PREFIX + name);
        if(obj == null) {
            obj = objectFactory.getObject();
            page.setAttribute(PREFIX + name, obj);
        }
        return obj;
    }

    public Object remove(String name) {
        Page page = getPage$SafeCall();
        if(page == null) return null;
        return page.removeAttribute(PREFIX + name);
    }

    public void registerDestructionCallback(String name, Runnable callback) {
    }

    public Object resolveContextualObject(String key) {
        return null;
    }

    public String getConversationId() {
        Page page = getPage$SafeCall();
        if(page == null) return "";
        return page.getId();
    }

}
