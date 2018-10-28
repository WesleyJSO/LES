package br.com.les.backend.security;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {
	
	@Autowired
	private GenericDAO<Employee> genericDAO;
	
	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	static final String EXPOSE_HEADER = "Access-Control-Expose-Headers";
	
	public void addAuthentication(HttpServletResponse response, String email) {
		String JWT = Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);		
		response.addHeader(EXPOSE_HEADER, HEADER_STRING);
		
	}
	
public Authentication getAuthentication(HttpServletRequest request) {
				
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			String email = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			
			User user = new User();
			Employee employee = new Employee();
			
			user.setEmail(email);
			employee.setUser(user);
			
			List<Employee> load = genericDAO.find(employee);
			if (load != null && load.size() > 0) {
				return new UsernamePasswordAuthenticationToken(load, null, load.get(0).getUser().getAuthorities());
			}
		}
		 
		
		return null;
	}


}
