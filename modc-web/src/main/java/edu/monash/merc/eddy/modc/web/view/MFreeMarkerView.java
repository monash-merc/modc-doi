package edu.monash.merc.eddy.modc.web.view;

import edu.monash.merc.eddy.modc.web.conts.MConts;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * Created by simonyu on 5/08/2014.
 */
public class MFreeMarkerView extends FreeMarkerView {
    /**
     * deployed base path
     */
    public static final String CONTEXT_PATH = "base";

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //we override the implementation.
        // expose model to JSP tags as request attributes
        exposeModelAsRequestAttributes(model, request);

        // Expose all standard FreeMarker hash models
        SimpleHash fmModel = buildTemplateModel(model, request, response);

        // get the Locale
        Locale locale = RequestContextUtils.getLocale(request);

		/*
         * by default. the view (html) is not generated unless you set the STATIC_HTML as true in the request.
		 */
        if (Boolean.TRUE.equals(model.get(MConts.STATIC_HTML))) {
            if (logger.isDebugEnabled()) {
                logger.debug("it's generating a html view in MFreeMarkerView.");
            }
            processStaticHTML(getTemplate(locale), fmModel, request, response);
        } else {
            //logger.info("====== Freemarker is processing the template now.");
            processTemplate(getTemplate(locale), fmModel, response);
        }
    }

    protected void processStaticHTML(Template template, SimpleHash model, HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, ServletException {
        // ApplicationContext context =
        // WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

        // base path
        String basePath = request.getSession().getServletContext().getRealPath("/");
        //logger.info("============ base path : " + basePath);
        String requestHTML = this.getRequestHTML(request);
        // the html output path
        String htmlPath = basePath + requestHTML;

        File htmlFile = new File(htmlPath);
        if (!htmlFile.getParentFile().exists()) {
            htmlFile.getParentFile().mkdirs();
        }
        // logger.info("==================== html file path: " + htmlPath);
        if (!htmlFile.exists()) {
            htmlFile.createNewFile();
            if (logger.isDebugEnabled()) {
                logger.debug("=====  create a new static html file");
            }
        }
        // create a Writer
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
        // process the template
        template.process(model, out);
        out.flush();
        out.close();

		/* dispatch the request to the generated static html page */
        request.getRequestDispatcher(requestHTML).forward(request, response);
    }


    /**
     *
     * get the statice file path
     * @param request HttpServletRequest
     * @return a file name
     */
    private String getRequestHTML(HttpServletRequest request) {
        // get context path -- normally the web app name
        String contextPath = request.getContextPath();
        // find request page
        String requestURI = request.getRequestURI();
        // if basePath contains web app name, just replace it
        requestURI = requestURI.replaceFirst(contextPath, "");
        // change the .do action as .htm, the send the request to this file
        requestURI = requestURI.substring(0, requestURI.indexOf(".")) + ".htm";
        if (logger.isDebugEnabled()) {
            logger.debug("request html url" + requestURI);
        }
        return requestURI;
    }


    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        super.exposeHelpers(model, request);

        // add the base path into the mode, so every page can get the base path
        model.put(CONTEXT_PATH, request.getContextPath());
        if (logger.isDebugEnabled()) {
            logger.debug("Finished to set the base path: " + request.getContextPath());
        }
    }
}
