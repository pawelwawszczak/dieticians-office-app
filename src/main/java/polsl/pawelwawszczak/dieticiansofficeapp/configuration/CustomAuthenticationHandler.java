package polsl.pawelwawszczak.dieticiansofficeapp.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.RoleRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {

    private final RoleRepository roleRepository;

    public CustomAuthenticationHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("emailAddress", authenticatedUser.getUsername());
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
                if(authority.getAuthority().equals("ROLE_ADMIN"))
                {
                    session.setAttribute("role", roleRepository.findByName("ROLE_ADMIN"));
                    try
                    {
                        httpServletResponse.sendRedirect("/admin/home");
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
                else if (authority.getAuthority().equals("ROLE_PATIENT"))
                {
                    session.setAttribute("role", roleRepository.findByName("ROLE_PATIENT"));
                    try
                    {
                        httpServletResponse.sendRedirect("/patient/home");
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }else if (authority.getAuthority().equals("ROLE_DIETICIAN"))
                {
                    session.setAttribute("role", roleRepository.findByName("ROLE_DIETICIAN"));
                    try
                    {
                        httpServletResponse.sendRedirect("/dietician/home");
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
        }
        );
    }
}
