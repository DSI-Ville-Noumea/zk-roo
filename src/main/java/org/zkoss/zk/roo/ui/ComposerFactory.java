package org.zkoss.zk.roo.ui;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.http.SimpleUiFactory;
import org.zkoss.zk.ui.util.Composer;

/**
 * @author Chanwit
 */
public class ComposerFactory extends SimpleUiFactory {

	@SuppressWarnings("rawtypes")
	@Override
	public Composer newComposer(Page page, Class clazz) {
		Composer composer = resolveBean(page, clazz.getName());
		if (composer == null) {
			composer = super.newComposer(page, clazz);
		}
		return composer;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Composer newComposer(Page page, String className) throws ClassNotFoundException {
		Composer composer = resolveBean(page, className);
		if (composer == null) {
			composer = super.newComposer(page, className);
		}
		return composer;
	}

	private String uncapitalize(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

	private Composer<?> resolveBean(Page page, String className) {
		ServletContext servletContext = page.getDesktop().getWebApp().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		String[] result = className.split("\\.");
		String beanName = uncapitalize(result[result.length-1]);
		if (ctx.containsBean(beanName)) {
			return (Composer<?>) ctx.getBean(beanName);
		} else {
			return null;
		}
	}
}