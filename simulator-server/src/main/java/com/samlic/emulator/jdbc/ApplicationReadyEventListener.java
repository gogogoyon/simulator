package com.samlic.emulator.jdbc;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import com.samlic.emulator.core.InterfaceCaseManager;
import com.samlic.emulator.dao.InterfaceCaseDao;
import com.samlic.emulator.entity.InterfaceCase;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationReadyEventListener.class);
	
	@Autowired
	private InterfaceCaseDao caseDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	private Resource initScript = new ClassPathResource("/init.sql");
	
	@Value("${spring.datasource.checkTableQuery}")
	private String checkTableQuery;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("On application event.");
		try {
			logger.info("checkTableQuery = " + checkTableQuery);
			jdbcTemplate.execute(checkTableQuery);			
		} catch(BadSqlGrammarException e) {
			logger.warn("Failed to check table.", e);
			if (e.getSQLException().getSQLState().equals("42X05")) {
				logger.info("Begin to create table.");
				doExecuteScript(initScript);
			}
		}	
		
		List<InterfaceCase> dataList = caseDao.queryAll();
		InterfaceCaseManager.init(dataList);
	}

	private void doExecuteScript(final Resource scriptResource) {
		if (scriptResource == null || !scriptResource.exists())
			return;
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {				
				String[] scripts;
				try {
					scripts = StringUtils.delimitedListToStringArray(stripComments(IOUtils.readLines(scriptResource
							.getInputStream())), ";");
				}
				catch (IOException e) {
					throw new BeanInitializationException("Cannot load script from [" + scriptResource + "]", e);
				}
				for (int i = 0; i < scripts.length; i++) {
					String script = scripts[i].trim();
					if (StringUtils.hasText(script)) {
						try {
							jdbcTemplate.execute(script);
						}
						catch (DataAccessException e) {
							logger.error("Failed to execute init script.", e);	
							if (script.toLowerCase().startsWith("drop")) {
								logger.debug("DROP script failed (ignoring): " + script);
							}
							else {
								throw e;
							}
						}
					}
				}				
			}

		});

	}

	private String stripComments(List<String> list) {
		StringBuffer buffer = new StringBuffer();
		for (String line : list) {
			if (!line.startsWith("//") && !line.startsWith("--")) {
				buffer.append(line + "\n");
			}
		}
		return buffer.toString();
	}

}
