package gov.usgs.cida.qw.webservice.codes;

import gov.usgs.cida.qw.QWConstants;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SiteTypeCodesMvcService extends AggregatedCodesMvcService {

    private static final Logger LOG = LoggerFactory.getLogger(SiteTypeCodesMvcService.class);

    @RequestMapping(value={"sitetypes", "sitetype"}, method=RequestMethod.GET, produces=QWConstants.MIME_TYPE_APPLICATION_XML)
    public void getSitetypes(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {
        LOG.debug("sitetypes");
        Map<String, List<String>> queryParams = new HashMap<>();
        doCodeRequest(request, outerFace, dataSource, queryParams, response);
    }

}
