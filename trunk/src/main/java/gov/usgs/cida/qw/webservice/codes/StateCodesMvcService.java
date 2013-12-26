package gov.usgs.cida.qw.webservice.codes;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StateCodesMvcService extends AggregatedCodesMvcService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value={"states", "statecode"}, method=RequestMethod.GET, produces="application/xml")
	public void getStates(final @RequestParam(value="countrycode", required=true) String[] countrycode,
			HttpServletRequest request,	HttpServletResponse response) {
		log.debug("states");
		Map<String, List<String>> queryParams = new HashMap<>();
		queryParams.put("countrycode", Arrays.asList(countrycode));
		doCodeRequest(request, outerFace, dataSource, queryParams, response);
	}
	
}