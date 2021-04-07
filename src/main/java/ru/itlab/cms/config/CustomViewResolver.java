package ru.itlab.cms.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

public class CustomViewResolver extends InternalResourceViewResolver {
    public static final String STATUS_URL_PREFIX = "status:";

    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        View view = super.createView(viewName, locale);

        return view != null ? view : getView(viewName, locale);
    }

    private View getView(String viewName, Locale locale) {
        // If this resolver is not supposed to handle the given view,
        // return null to pass on to the next resolver in the chain.
        if (!canHandle(viewName, locale)) {
            return null;
        }

//        // Check for special "redirect:" prefix.
//        if (viewName.startsWith(REDIRECT_URL_PREFIX)) {
//            String redirectUrl = viewName.substring(REDIRECT_URL_PREFIX.length());
//            RedirectView view = new RedirectView(redirectUrl,
//                    isRedirectContextRelative(), isRedirectHttp10Compatible());
//            String[] hosts = getRedirectHosts();
//            if (hosts != null) {
//                view.setHosts(hosts);
//            }
//            return applyLifecycleMethods(REDIRECT_URL_PREFIX, view);
//        }
//
//        // Check for special "forward:" prefix.
//        if (viewName.startsWith(FORWARD_URL_PREFIX)) {
//            String forwardUrl = viewName.substring(FORWARD_URL_PREFIX.length());
//            InternalResourceView view = new InternalResourceView(forwardUrl);
//            return applyLifecycleMethods(FORWARD_URL_PREFIX, view);
//        }

        // Check for special "status:" prefix
        if (viewName.startsWith(STATUS_URL_PREFIX)) {
            String statusCode = viewName.substring(STATUS_URL_PREFIX.length());
            logger.info("Status code " + statusCode);
            int code = -1;
            try {
                code = Integer.parseInt(statusCode);
            } catch (NumberFormatException e) {
                logger.error(e);
            }
            logger.info("Code " + code);
            switch (code) {
                case 404:
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor Not Found");
            }
            logger.info("Error doesn't work");
        }

        return null;
    }
}
