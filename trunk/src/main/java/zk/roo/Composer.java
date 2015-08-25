package zk.roo;

import org.zkoss.zk.roo.composer.RooComposer;
import org.zkoss.zk.roo.query.Query;
import org.zkoss.zk.ui.select.Selectors;

public class Composer extends RooComposer {

    public Query $(String selector) {
        // TODO better select scope
        return new Query(Selectors.find(this.getPage().getFirstRoot(), selector));
    }

}
