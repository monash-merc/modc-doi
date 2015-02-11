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
     * 计算要生成的静态文件相对路径 因为大家在调试的时候一般在Tomcat的webapps下面新建站点目录的， 但在实际应用时直接布署到ROOT目录里面,这里要保证路径的一致性。
     *
     * @param request HttpServletRequest
     * @return /目录/*.htm
     */
    private String getRequestHTML(HttpServletRequest request) {
        // web应用名称,部署在ROOT目录时为空
        String contextPath = request.getContextPath();
        // web应用/目录/文件.do
        String requestURI = request.getRequestURI();
        // basePath里面已经有了web应用名称，所以直接把它replace掉，以免重复
        requestURI = requestURI.replaceFirst(contextPath, "");
        // 将.do改为.htm,稍后将请求转发到此htm文件
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
