package basepackage.core.components.crm.adapters;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

import basepackage.core.common.exception.SystemErrorCode;
import basepackage.core.common.exception.SystemException;
import basepackage.core.config.CoreProperties;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    CoreProperties coreProperties;
    
    @Override
    public CustomerEntity save(CustomerEntity entity) {
	Connection connection = null;

	try {
	    Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("ID", entity.getId());
	    parameters.put("FIRST_NAME", entity.getFirstName());
	    parameters.put("LAST_NAME", entity.getLastName());
	    parameters.put("EMAIL", entity.getEmail());

	    JndiTemplate jndiTemplate = new JndiTemplate();
	    String crmDatasourceJndiName = coreProperties.getPropertyValue("crm.datasource.jndiName");
	    DataSource dataSource = (DataSource) jndiTemplate.lookup(crmDatasourceJndiName);

	    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
		                                    .withTableName("CUSTOMER")
		                                    .usingGeneratedKeyColumns("ID");
	    Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
	    entity.setId(id.intValue());
	    
	    return entity;
	} catch (Exception ex1) {
	    throw new SystemException(ex1, SystemErrorCode.DATA_ACCESS_ERROR_CODE, "ERROR saving CustomerEntity");
	} finally {
	    if (connection != null) {
		try {
		    connection.close();
		} catch (Exception ex2) {
		    throw new SystemException(ex2, SystemErrorCode.DATA_ACCESS_ERROR_CODE, "ERROR closing connection, saving CustomerEntity");
		}
	    }
	}
    }

    @Override
    public List<CustomerEntity> findAll() {
	Connection connection = null;

	try {

	    RowMapper<CustomerEntity> customerRowMapper = (rs, rowNum) -> {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(rs.getInt("ID"));
		customerEntity.setFirstName(rs.getString("FIRST_NAME"));
		customerEntity.setLastName(rs.getString("LAST_NAME"));
		customerEntity.setEmail(rs.getString("EMAIL"));
		
		return customerEntity;
	    };

	    JndiTemplate jndiTemplate = new JndiTemplate();
	    String crmDatasourceJndiName = coreProperties.getPropertyValue("crm.datasource.jndiName");
	    DataSource dataSource = (DataSource) jndiTemplate.lookup(crmDatasourceJndiName);
	    
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    List<CustomerEntity> allCustomersList = jdbcTemplate.query("select * from CUSTOMER", new Object[] {}, customerRowMapper);
	    
	    return allCustomersList;
	} catch (Exception ex1) {
	    throw new SystemException(ex1, SystemErrorCode.DATA_ACCESS_ERROR_CODE, "ERROR querying Customers");
	} finally {
	    if (connection != null) {
		try {
		    connection.close();
		} catch (Exception ex2) {
		    throw new SystemException(ex2, SystemErrorCode.DATA_ACCESS_ERROR_CODE, "ERROR closing connection, querying Customers");
		}
	    }
	}
    }

}
