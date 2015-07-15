package gov.usgs.cida.qw.codes.webservices;

import gov.usgs.cida.qw.LastUpdateDao;
import gov.usgs.cida.qw.codes.Code;
import gov.usgs.cida.qw.codes.CodeList;
import gov.usgs.cida.qw.codes.CodeType;
import gov.usgs.cida.qw.codes.dao.CodeDao;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(value={"codes/subjectTaxonomicNames", "codes/subjectTaxonomicName"}, produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class SubjectTaxonomicNameRestController extends CodesRestController {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectTaxonomicNameRestController.class);

    @Autowired
	public SubjectTaxonomicNameRestController(@Qualifier("lastUpdateDao") final LastUpdateDao lastUpdateDao,
			@Qualifier("codeDao") final CodeDao codeDao) {
    	this.lastUpdateDao = lastUpdateDao;
    	this.codeDao = codeDao;
    }

    @RequestMapping(params="!value", method=RequestMethod.GET)
    public CodeList getTaxonomicNames(final @RequestParam(value="text", required=false) String text,
    		final @RequestParam(value="pagenumber", required=false) String pageNumber,
    		final @RequestParam(value="pagesize", required=false) String pageSize,
    		WebRequest webRequest) {
        LOG.debug("subjectTaxonomicNames");
        return getList(CodeType.SUBJECTTAXONOMICNAME, text, pageNumber, pageSize, null, webRequest);
    }

    @RequestMapping(params="value", method=RequestMethod.GET)
    public Code getTaxonomicName(final @RequestParam(value="value") String value,
    		WebRequest webRequest, HttpServletResponse response) {
        LOG.debug("subjectTaxonomicName");
        return getCode(CodeType.SUBJECTTAXONOMICNAME, value, webRequest, response);
    }

}