package cz.uhk.restaurace.web.i18n;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.uhk.restaurace.web.AdminController;
import cz.uhk.restaurace.web.CustomerOrderController;
import cz.uhk.restaurace.web.DishController;
import cz.uhk.restaurace.web.ShiftController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;


public class LocaleChangeInterceptorDB extends HandlerInterceptorAdapter {
	
	@Autowired
	private DishController dishController;

	@Autowired
	private CustomerOrderController customerOrderController;

	@Autowired
	private ShiftController shiftController;
	
	@Autowired
	private AdminController adminController;

	public static final String DEFAULT_PARAM_NAME = "locale";

	private String paramName = DEFAULT_PARAM_NAME;

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {

		String newLocale = request.getParameter(this.paramName);

		if (newLocale != null) {
			
			dishController.setLanguage(newLocale, request.getSession());
			customerOrderController.setLanguage(newLocale, request.getSession());
			shiftController.setLanguage(newLocale);
			adminController.setLanguage(newLocale);
			LocaleResolver localeResolver = RequestContextUtils
					.getLocaleResolver(request);

			if (localeResolver == null) {

				throw new IllegalStateException(
						"No LocaleResolver found: not in a DispatcherServlet request?");
			}

			LocaleEditor localeEditor = new LocaleEditor();

			localeEditor.setAsText(newLocale);

			localeResolver.setLocale(request, response,
					(Locale) localeEditor.getValue());
		}

		// Proceed in any case.

		return true;
	}

}
