package org.zkoss.zk.roo.composer;

import java.lang.reflect.Method;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

public class RooComposer extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        try {
            Method afterCompose = this.getClass().getDeclaredMethod("afterCompose", Component.class);
            if(afterCompose != null) {
                afterCompose.invoke(this, comp);
            }
        } catch(Throwable e) {
            // do nothing
        }
    }

}
