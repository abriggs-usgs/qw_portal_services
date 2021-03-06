
package gov.usgs.cida.qw.codes.webservices;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import gov.usgs.cida.qw.BaseRestController;
import gov.usgs.cida.qw.LastUpdateDao;
import gov.usgs.cida.qw.codes.Code;
import gov.usgs.cida.qw.codes.CodeList;
import gov.usgs.cida.qw.codes.CodeType;
import gov.usgs.cida.qw.codes.dao.CodeDao;
import gov.usgs.cida.qw.swagger.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@Api(tags={SwaggerConfig.MONITORING_LOCATION_TAG_NAME})
@RestController
@RequestMapping(value="codes/monitoringlocation", produces={BaseRestController.MEDIA_TYPE_APPLICATION_XML_UTF8_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MonitoringLocationRestController extends CodesRestController {

	private static final Logger LOG = LoggerFactory.getLogger(MonitoringLocationRestController.class);

	@Autowired
	public MonitoringLocationRestController(final LastUpdateDao lastUpdateDao, final CodeDao codeDao) {
		this.lastUpdateDao = lastUpdateDao;
		this.codeDao = codeDao;
	}

	@ApiOperation(value="Return a filtered and paged list of valid Monitoring Locations.")
	@GetMapping()
	public CodeList getMonitoringLocations(final @RequestParam(value="organizationid", required=false) String [] organizationid,
			final @RequestParam(value="provider", required=false) String [] provider, 
			final @RequestParam(value="text", required=false) String text,
			final @RequestParam(value="pagenumber", required=false, defaultValue="1") String pageNumber,
			final @RequestParam(value="pagesize", required=false, defaultValue="25") String pageSize,
			@ApiIgnore WebRequest webRequest) {
		LOG.debug("monitoringlocations");
		Map<String, Object> addlParms = new HashMap<>();
		addlParms.put("organizationid", organizationid);
		addlParms.put("provider", provider);
		return getList(CodeType.MONITORINGLOCATION, text, pageNumber, pageSize, addlParms, webRequest);
	}

	@ApiOperation(value="Validate and return the requested Monitoring Location.")
	@GetMapping("/validate")
	public Code getMonitoringLocation(final @RequestParam(value="value") String value, @ApiIgnore WebRequest webRequest, HttpServletResponse response) {
		LOG.debug("monitoringlocation");
		return getCode(CodeType.MONITORINGLOCATION, value, webRequest, response);
	}

}
