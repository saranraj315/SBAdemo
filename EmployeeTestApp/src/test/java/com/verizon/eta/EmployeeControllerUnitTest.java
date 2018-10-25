package com.verizon.eta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.verizon.eta.restapi.EmployeeController;
import com.verizon.eta.service.EmployeeService;
import com.verizon.eta.testUtil.TestUtil;

import jdk.nashorn.internal.runtime.ECMAException;

import com.verizon.eta.model.Employee;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerUnitTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webAppContext;
	
	@MockBean
	private EmployeeService empServiceMock;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@After
	public void tearDown() {
		mockMvc = null;
	}
	
	
	
	@Test
	public void testGetAllEmployees() throws Exception{
		assertThat(this.empServiceMock).isNotNull();
		
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee());
		
		when(empServiceMock.getAllEmployees()).thenReturn(empList);
		
		this.mockMvc.perform(get("/employees"))
					.andExpect(status().isOk())
					.andDo(print());
		
		this.mockMvc.perform(post("/employees"))
					.andExpect(status().is4xxClientError())
					.andDo(print());
	}
	

	
	@Test
	public void testGetEmployeeById() throws Exception{
		assertThat(this.empServiceMock).isNotNull();
		
		Employee emp = new Employee();
		int id = 101;
		
		when(empServiceMock.getEmployeeById(id)).thenReturn(emp);
		
		this.mockMvc.perform(get("/employees/"+id))
					.andExpect(status().isOk())
					.andDo(print());
			
		this.mockMvc.perform(post("/employees/"+id))
					.andExpect(status().is4xxClientError())
					.andDo(print());
		
	}
	
	
	@Test
	public void testAddEmployee() throws Exception{
		assertThat(this.empServiceMock).isNotNull();
		
		Employee emp = new Employee();
		
		when(empServiceMock.addEmployee(Mockito.any(Employee.class))).thenReturn(emp);
			
		this.mockMvc.perform(post("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
												.content(TestUtil.convertObjectToJsonBytes(emp)))
					.andDo(print())
					.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void testUpdateEmployee() throws Exception{
		assertThat(this.empServiceMock).isNotNull();
		
		Employee emp = new Employee();
		
		when(empServiceMock.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp);
		
		this.mockMvc.perform(put("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
												.content(TestUtil.convertObjectToJsonBytes(emp)))
					.andDo(print())
					.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void testDeleteEmployee() throws Exception{
		assertThat(this.empServiceMock).isNotNull();
		
		int id = 101;
		
		when(empServiceMock.deleteEmployeeById(id)).thenReturn(true);
		
		this.mockMvc.perform(delete("/employees/"+ id))
					.andExpect(status().isOk())
					.andDo(print());
	}
	
	
}
