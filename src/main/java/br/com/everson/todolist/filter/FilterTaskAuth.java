package br.com.everson.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.everson.todolist.user.IUserRepository;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Pegar a autenticação do usuário (usuario e senha)
        var authorization = request.getHeader("Authorization");

        var authEncoded = authorization.substring("Basic".length()).trim();

        byte[] authDecode = Base64.getDecoder().decode(authEncoded);

        var authString = new String(authDecode);
        System.out.println("Authorization: ");
        System.out.println(authString);

        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println("Authorization: ");
        System.out.println(username);
        System.out.println(password);
        
        // validar o usuário
        var user =this.userRepository.findByUserName(username);
        if(user == null){
            response.sendError(401);
        }else{
        // validar a senha
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(),user.getPassword());
        if(passwordVerify.verified){
            filterChain.doFilter(request, response);
        }else{
            response.sendError(401);
        }
        // Se válido, permitir o acesso

        filterChain.doFilter(request, response);
        
       filterChain.doFilter(request, response);
    }
}
}
            
